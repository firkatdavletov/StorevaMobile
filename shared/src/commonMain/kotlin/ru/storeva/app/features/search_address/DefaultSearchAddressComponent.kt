package ru.storeva.app.features.search_address

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.domain.models.AddressModel
import ru.storeva.app.domain.models.DeliveryInfoModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.DepartmentModel
import ru.storeva.app.domain.models.GeoAddressModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.usecase.cart.CreateCartUseCase
import ru.storeva.app.domain.usecase.cart.LoadCartUseCase
import ru.storeva.app.domain.usecase.cart.UpdateDeliveryAddressUseCase
import ru.storeva.app.domain.usecase.departments.GetDepartmentsUseCase
import ru.storeva.app.domain.usecase.geo.GetGeoAddressUseCase
import ru.storeva.app.domain.usecase.geo.SearchAddressUseCase
import ru.storeva.app.features.home.HomeComponentOld
import ru.storeva.app.features.launch.presentation.LaunchComponent
import ru.storeva.app.features.map.MapComponent
import ru.storeva.app.features.payment.PaymentComponent
import ru.storeva.app.features.utils.DistanceCalculator

class DefaultSearchAddressComponent(
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
    private val fromScreen: String?,
    private val cartRepository: CartRepository,
    private val getDepartmentsUseCase: GetDepartmentsUseCase,
    private val searchAddressUseCase: SearchAddressUseCase,
    private val updateDeliveryAddressUseCase: UpdateDeliveryAddressUseCase,
    private val getGeoAddressUseCase: GetGeoAddressUseCase,
    private val createCartUseCase: CreateCartUseCase,
    private val loadCartUseCase: LoadCartUseCase,
    private val callbacks: SearchAddressCallbacks,
) : SearchAddressComponent(
        componentContext = componentContext,
        snackBarManager = snackBarManager,
        initialState = SearchAddressViewState(
            isLoading = false,
            isSearching = false,
            query = "",
            deliveryType = DeliveryType.PICKUP,
            departments = emptyList(),
            addresses = emptyList(),
        ),
        reducer = SearchAddressReducer(),
    ) {
    private var job: Job? = null
    private var cartSubjectJob: Job? = null
    private var searchSubjectJob: Job? = null
    private val searchFlow = MutableSharedFlow<String>()
    private var _departments: List<DepartmentModel> = emptyList()

    override fun onEvent(event: SearchAddressViewEvent) {
        when (event) {
            SearchAddressViewEvent.OnBackClicked -> {
                callbacks.navigateBack()
            }

            is SearchAddressViewEvent.OnQueryChanged -> {
                reduce(event)
                coroutineScope.launch {
                    searchFlow.emit(event.query)
                }
            }

            is SearchAddressViewEvent.OnCartLoaded -> {
                reduce(event)
            }

            is SearchAddressViewEvent.OnDepartmentsLoaded -> {
                reduce(event)
            }

            is SearchAddressViewEvent.OnSearchComplete -> {
                reduce(event)
            }

            is SearchAddressViewEvent.OnAddressClicked -> {
                if (event.address.uri == null) {
                    onEvent(SearchAddressViewEvent.OnError("Нет информации об адресе"))
                }
                getGeoAddress(event.address.uri!!, event.address.entrance)
            }

            SearchAddressViewEvent.OnMapClicked -> {
                if (fromScreen == MapComponent::class.simpleName) {
                    callbacks.navigateBack()
                } else {
                    callbacks.navigateToMap(fromScreen)
                }
            }

            is SearchAddressViewEvent.OnError -> {
                showError(event.message)
                reduce(event)
            }

            is SearchAddressViewEvent.OnThrowError -> {
                showThrowError(event.throwable)
                reduce(event)
            }
        }
    }

    override fun onStart() {
        getDepartments()
        subscribeToCart()
        subscribeToSearch()
    }

    override fun onStop() {
        cartSubjectJob?.cancel()
        cartSubjectJob = null
        searchSubjectJob?.cancel()
        searchSubjectJob = null
    }

    private fun subscribeToCart() {
        cartSubjectJob?.cancel()
        cartSubjectJob = coroutineScope.launch {
            cartRepository.cartSubject.collect {
                onEvent(SearchAddressViewEvent.OnCartLoaded(it))
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun subscribeToSearch() {
        searchSubjectJob?.cancel()
        searchSubjectJob = coroutineScope.launch {
            searchFlow
                .debounce(1000)
                .filter { query ->
                    if (query.isBlank()) {
                        onEvent(SearchAddressViewEvent.OnSearchComplete(emptyList()))
                        false
                    } else {
                        true
                    }
                }.collect {
                    search(it)
                }
        }
    }

    private fun search(query: String) {
        job?.cancel()
        job = coroutineScope.launch {
            println("searching: $query")

            searchAddressUseCase
                .invoke(query)
                .catch {
                    onEvent(SearchAddressViewEvent.OnThrowError(it))
                }.collect { resultModel ->
                    when (resultModel) {
                        is ResultModel.Error -> {
                            onEvent(SearchAddressViewEvent.OnError(resultModel.message))
                        }

                        ResultModel.Loading -> {}

                        is ResultModel.Success<List<GeoAddressModel>> -> {
                            onEvent(SearchAddressViewEvent.OnSearchComplete(resultModel.data))
                        }
                    }
                }
        }
    }

    private fun getDepartments() {
        coroutineScope.launch {
            getDepartmentsUseCase
                .invoke(Unit)
                .catch {
                    onEvent(SearchAddressViewEvent.OnThrowError(it))
                }.collect { departmentModels ->
                    _departments = departmentModels
                    onEvent(SearchAddressViewEvent.OnDepartmentsLoaded(departmentModels))
                }
        }
    }

    private suspend fun updateAddress(
        geoAddress: GeoAddressModel,
        departmentModel: DepartmentModel,
    ) {
        val addressModel = AddressModel(
            street = geoAddress.street,
            house = geoAddress.house,
            entrance = geoAddress.entrance,
            city = geoAddress.city,
            latitude = geoAddress.latitude,
            longitude = geoAddress.longitude,
        )
        val params = UpdateDeliveryAddressUseCase.Params(
            deliveryType = DeliveryType.DELIVERY,
            deliveryAddress = addressModel,
            departmentId = departmentModel.id,
            deliveryInfo = geoAddress.deliveryInfo ?: DeliveryInfoModel(0, 0),
        )
        updateDeliveryAddressUseCase
            .invoke(params)
            .catch {
                onEvent(SearchAddressViewEvent.OnThrowError(it))
            }.collect { resultModel ->
                when (resultModel) {
                    is ResultModel.Error -> {
                        onEvent(SearchAddressViewEvent.OnError(resultModel.message))
                    }

                    ResultModel.Loading -> {}

                    is ResultModel.Success<Boolean> -> {
                        when (fromScreen) {
                            HomeComponentOld::class.simpleName -> {
                                withContext(Dispatchers.Main) {
                                    callbacks.navigateToHome()
                                }
                            }

                            PaymentComponent::class.simpleName -> {
                                withContext(Dispatchers.Main) {
                                    callbacks.navigateToPayment()
                                }
                            }

                            else -> {
                                withContext(Dispatchers.Main) {
                                    callbacks.navigateToHome()
                                }
                            }
                        }
                    }
                }
            }
    }

    private fun getGeoAddress(
        uri: String,
        entrance: Int?,
    ) {
        val param = GetGeoAddressUseCase.Params(uri, entrance)
        coroutineScope.launch {
            getGeoAddressUseCase
                .invoke(param)
                .catch {
                    onEvent(SearchAddressViewEvent.OnThrowError(it))
                }.collect { resultModel ->
                    when (resultModel) {
                        is ResultModel.Error -> {
                            onEvent(SearchAddressViewEvent.OnError(resultModel.message))
                        }

                        ResultModel.Loading -> {}

                        is ResultModel.Success<GeoAddressModel> -> {
                            val closestDepartment = findClosestDepartment(
                                resultModel.data.latitude,
                                resultModel.data.longitude,
                                _departments,
                            ) ?: return@collect

                            when (fromScreen) {
                                LaunchComponent::class.simpleName -> {
                                    createCart(resultModel.data, closestDepartment)
                                }

                                else -> {
                                    updateAddress(resultModel.data, closestDepartment)
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun createCart(
        geoAddress: GeoAddressModel,
        closestDepartment: DepartmentModel?,
    ) {
        val deliveryType = DeliveryType.DELIVERY
        val deliveryInfo = geoAddress.deliveryInfo

        val params = when (deliveryType) {
            DeliveryType.PICKUP -> {
                if (closestDepartment != null) {
                    CreateCartUseCase.Params(
                        departmentId = closestDepartment.id,
                    )
                } else {
                    onEvent(SearchAddressViewEvent.OnError("Не выбран ресторан"))
                    null
                }
            }

            DeliveryType.DELIVERY -> {
                if (deliveryInfo != null && closestDepartment != null) {
                    CreateCartUseCase.Params(
                        deliveryAddress = AddressModel(
                            city = geoAddress.city,
                            street = geoAddress.street,
                            house = geoAddress.house,
                            entrance = geoAddress.entrance,
                            flat = null,
                            intercome = null,
                            comment = null,
                            latitude = geoAddress.latitude,
                            longitude = geoAddress.longitude,
                        ),
                        deliveryInfo = deliveryInfo,
                        departmentId = closestDepartment.id,
                    )
                } else {
                    if (deliveryInfo == null) {
                        onEvent(
                            SearchAddressViewEvent.OnError("Ошибка выбора адреса доставки: нет информации о доставке"),
                        )
                    }
                    if (closestDepartment == null) {
                        onEvent(
                            SearchAddressViewEvent.OnError("Ошибка выбора адреса доставки: нет ближайшего ресторана"),
                        )
                    }

                    null
                }
            }
        }

        if (params == null) return

        coroutineScope.launch {
            createCartUseCase
                .invoke(params)
                .catch {
                    onEvent(SearchAddressViewEvent.OnThrowError(it))
                }.collect { result ->
                    when (result) {
                        is ResultModel.Error -> {
                            onEvent(SearchAddressViewEvent.OnError(result.message))
                        }

                        ResultModel.Loading -> {
                        }

                        is ResultModel.Success<Boolean> -> {
                            loadCart {
                                callbacks.navigateToHome()
                            }
                        }
                    }
                }
        }
    }

    private suspend fun loadCart(onSuccess: () -> Unit = {}) {
        loadCartUseCase(Unit)
            .catch {
                onEvent(SearchAddressViewEvent.OnThrowError(it))
            }.collect { result ->
                withContext(Dispatchers.Main) {
                    when (result) {
                        is ResultModel.Error -> {
                            onEvent(SearchAddressViewEvent.OnError(result.message))
                        }

                        ResultModel.Loading -> {
                        }

                        is ResultModel.Success<Boolean> -> {
                            onSuccess()
                        }
                    }
                }
            }
    }

    private fun findClosestDepartment(
        lat: Double,
        lon: Double,
        departments: List<DepartmentModel>,
    ): DepartmentModel? {
        return departments.minByOrNull { department ->
            DistanceCalculator.haversineDistance(lat, lon, department.latitude, department.longitude)
        }
    }
}