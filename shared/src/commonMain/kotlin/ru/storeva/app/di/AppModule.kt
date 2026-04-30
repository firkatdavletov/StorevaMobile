package ru.storeva.app.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.data.HttpException
import ru.storeva.app.data.api.auth_api.AuthApi
import ru.storeva.app.data.api.auth_api.AuthApiImpl
import ru.storeva.app.data.api.auth_api.model.RefreshTokenRequestBody
import ru.storeva.app.data.api.cart_api.CartApi
import ru.storeva.app.data.api.cart_api.CartApiImpl
import ru.storeva.app.data.api.catalog.CatalogApi
import ru.storeva.app.data.api.catalog.CatalogApiImpl
import ru.storeva.app.data.api.departments_api.DepartmentApi
import ru.storeva.app.data.api.departments_api.DepartmentApiImpl
import ru.storeva.app.data.api.map_api.MapApi
import ru.storeva.app.data.api.map_api.MapApiImpl
import ru.storeva.app.data.api.order_api.OrderApi
import ru.storeva.app.data.api.order_api.OrderApiImpl
import ru.storeva.app.data.api.payment_api.PaymentApi
import ru.storeva.app.data.api.payment_api.PaymentApiImpl
import ru.storeva.app.data.api.security.AuthPlugin
import ru.storeva.app.data.api.user_api.UserApi
import ru.storeva.app.data.api.user_api.UserApiImpl
import ru.storeva.app.data.datastore.local.DefaultDepartmentLocalDataStore
import ru.storeva.app.data.datastore.local.DepartmentsLocalDataStore
import ru.storeva.app.data.datastore.local.SecurityStorage
import ru.storeva.app.data.datastore.local.catalog.DefaultLocalCatalogDataStore
import ru.storeva.app.data.datastore.local.catalog.LocalCatalogDataStore
import ru.storeva.app.data.datastore.local.user.DefaultUserLocalDataStore
import ru.storeva.app.data.datastore.local.user.UserLocalDataStore
import ru.storeva.app.data.datastore.remote.auth.AuthRemoteDataStore
import ru.storeva.app.data.datastore.remote.auth.DefaultAuthRemoteDatStore
import ru.storeva.app.data.datastore.remote.cart.DefaultRemoteCartDataStore
import ru.storeva.app.data.datastore.remote.cart.RemoteCartDataStore
import ru.storeva.app.data.datastore.remote.catalog.CatalogRemoteDataStore
import ru.storeva.app.data.datastore.remote.catalog.DefaultCatalogRemoteDataStore
import ru.storeva.app.data.datastore.remote.departments.DefaultDepartmentsRemoteDataStore
import ru.storeva.app.data.datastore.remote.departments.DepartmentsRemoteDataStore
import ru.storeva.app.data.datastore.remote.geo.DefaultGeoRemoteDatasource
import ru.storeva.app.data.datastore.remote.geo.GeoRemoteDatasource
import ru.storeva.app.data.datastore.remote.order.DefaultOrderRemoteDataStore
import ru.storeva.app.data.datastore.remote.order.OrderRemoteDataStore
import ru.storeva.app.data.datastore.remote.payment.DefaultPaymentRemoteDataStore
import ru.storeva.app.data.datastore.remote.payment.PaymentRemoteDataStore
import ru.storeva.app.data.datastore.remote.user.DefaultUserRemoteDatastore
import ru.storeva.app.data.datastore.remote.user.UserRemoteDataStore
import ru.storeva.app.data.mapper.AddressModelMapper
import ru.storeva.app.data.mapper.AuthTypeMapper
import ru.storeva.app.data.mapper.BankInfoMapper
import ru.storeva.app.data.mapper.CartItemMapper
import ru.storeva.app.data.mapper.CartMapper
import ru.storeva.app.data.mapper.CategoryMapper
import ru.storeva.app.data.mapper.CityMapper
import ru.storeva.app.data.mapper.DeliveryInfoMapper
import ru.storeva.app.data.mapper.DepartmentMapper
import ru.storeva.app.data.mapper.GeoAddressMapper
import ru.storeva.app.data.mapper.OrderItemMapper
import ru.storeva.app.data.mapper.OrderMapper
import ru.storeva.app.data.mapper.PaymentMapper
import ru.storeva.app.data.mapper.ProductMapper
import ru.storeva.app.data.mapper.TokenPairMapper
import ru.storeva.app.data.mapper.UserMapper
import ru.storeva.app.data.mapper.WorkingHoursMapper
import ru.storeva.app.data.repositories.auth.DefaultAuthRepository
import ru.storeva.app.data.repositories.cart.DefaultCartRepository
import ru.storeva.app.data.repositories.catalog.DefaultCatalogRepository
import ru.storeva.app.data.repositories.departments.DefaultDepartmentRepository
import ru.storeva.app.data.repositories.geo.DefaultGeoRepository
import ru.storeva.app.data.repositories.order.DefaultOrderRepository
import ru.storeva.app.data.repositories.payment.DefaultPaymentRepository
import ru.storeva.app.data.repositories.sbp_banks.DefaultSbpBanksRepository
import ru.storeva.app.data.repositories.token.DefaultTokenRepository
import ru.storeva.app.data.repositories.user.DefaultUserRepository
import ru.storeva.app.domain.repositories.AuthRepository
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.repositories.CatalogRepository
import ru.storeva.app.domain.repositories.DepartmentsRepository
import ru.storeva.app.domain.repositories.GeoRepository
import ru.storeva.app.domain.repositories.OrderRepository
import ru.storeva.app.domain.repositories.PaymentRepository
import ru.storeva.app.domain.repositories.SbpBanksRepository
import ru.storeva.app.domain.repositories.TokenRepository
import ru.storeva.app.domain.repositories.UserRepository
import ru.storeva.app.domain.usecase.auth.GetAccessTokenUseCase
import ru.storeva.app.domain.usecase.auth.GetAuthTypesUseCase
import ru.storeva.app.domain.usecase.auth.VerifyCodeUseCase
import ru.storeva.app.domain.usecase.auth.VerifyPhoneNumberUseCase
import ru.storeva.app.domain.usecase.cart.AddToCartUseCase
import ru.storeva.app.domain.usecase.cart.ClearCartUseCase
import ru.storeva.app.domain.usecase.cart.CreateCartUseCase
import ru.storeva.app.domain.usecase.cart.LoadCartUseCase
import ru.storeva.app.domain.usecase.cart.RemoveFromCartUseCase
import ru.storeva.app.domain.usecase.cart.UpdateDeliveryAddressUseCase
import ru.storeva.app.domain.usecase.catalog.GetCategoriesUseCase
import ru.storeva.app.domain.usecase.catalog.GetProductCardUseCase
import ru.storeva.app.domain.usecase.catalog.GetProductsUseCase
import ru.storeva.app.domain.usecase.catalog.LoadCatalogUseCase
import ru.storeva.app.domain.usecase.departments.GetDepartmentsUseCase
import ru.storeva.app.domain.usecase.geo.GetGeoAddressUseCase
import ru.storeva.app.domain.usecase.geo.SearchAddressUseCase
import ru.storeva.app.domain.usecase.order.CreateOrderUseCase
import ru.storeva.app.domain.usecase.order.GetCurrentOrderUseCase
import ru.storeva.app.domain.usecase.order.GetOrderByIdUseCase
import ru.storeva.app.domain.usecase.order.GetOrdersUseCase
import ru.storeva.app.domain.usecase.payment.GetPaymentTypesUseCase
import ru.storeva.app.domain.usecase.sbp_banks.GetSbpBanksUseCase
import ru.storeva.app.domain.usecase.user.DeleteUserUseCase
import ru.storeva.app.domain.usecase.user.LoadUserUseCase
import ru.storeva.app.domain.usecase.user.LogoutUserUseCase
import ru.storeva.app.domain.usecase.user.UpdateUserUseCase
import ru.storeva.app.features.mapper.OrderUIModelMapper
import ru.storeva.generated.BuildTenantConfig

// For IOS:
// private const val BASE_URL = "localhost:8080"

// For Android Studio
// private const val BASE_URL = "10.0.2.2:8080"
// For remote server
private const val BASE_URL = BuildTenantConfig.API_BASE_URL
private val isHttps = true

@OptIn(ExperimentalSerializationApi::class)
fun appModule() =
    module {
        includes(commonModule)

        single<SnackBarManager> { SnackBarManager() }
        // Data stores
        single<AuthRemoteDataStore> { DefaultAuthRemoteDatStore(get()) }
        single<UserRemoteDataStore> { DefaultUserRemoteDatastore(get()) }
        single<UserLocalDataStore> { DefaultUserLocalDataStore() }
        single<CatalogRemoteDataStore> { DefaultCatalogRemoteDataStore(get()) }
        single<LocalCatalogDataStore> { DefaultLocalCatalogDataStore() }
        single<GeoRemoteDatasource> { DefaultGeoRemoteDatasource(get()) }
        single<RemoteCartDataStore> { DefaultRemoteCartDataStore(get(), get()) }
        single<DepartmentsRemoteDataStore> { DefaultDepartmentsRemoteDataStore(get()) }
        single<DepartmentsLocalDataStore> { DefaultDepartmentLocalDataStore() }
        single<PaymentRemoteDataStore> { DefaultPaymentRemoteDataStore(get()) }
        single<OrderRemoteDataStore> { DefaultOrderRemoteDataStore(get()) }

        // Repositories
        single<AuthRepository> { DefaultAuthRepository(get(), get(), get(), get()) }
        single<UserRepository> { DefaultUserRepository(get(), get(), get()) }
        single<CatalogRepository> { DefaultCatalogRepository(get(), get(), get(), get()) }
        single<TokenRepository> { DefaultTokenRepository(get()) }
        single<CartRepository> { DefaultCartRepository(get(), get(), get(), get(), get()) }
        single<GeoRepository> { DefaultGeoRepository(get(), get()) }
        single<DepartmentsRepository> { DefaultDepartmentRepository(get(), get(), get()) }
        single<PaymentRepository> { DefaultPaymentRepository(get(), get()) }
        single<SbpBanksRepository> { DefaultSbpBanksRepository(get(), get()) }
        single<OrderRepository> { DefaultOrderRepository(get(), get(), get(), get()) }

        // UseCases
        factory<LoadUserUseCase> { LoadUserUseCase(get()) }
        factory<GetAuthTypesUseCase> { GetAuthTypesUseCase(get()) }
        factory<GetAccessTokenUseCase> { GetAccessTokenUseCase(get()) }
        factory<VerifyPhoneNumberUseCase> { VerifyPhoneNumberUseCase(get()) }
        factory<VerifyCodeUseCase> { VerifyCodeUseCase(get()) }
        factory<GetCategoriesUseCase> { GetCategoriesUseCase(get()) }
        factory<GetProductsUseCase> { GetProductsUseCase(get()) }
        factory<AddToCartUseCase> { AddToCartUseCase(get()) }
        factory<LoadCartUseCase> { LoadCartUseCase(get()) }
        factory<RemoveFromCartUseCase> { RemoveFromCartUseCase(get()) }
        factory<GetGeoAddressUseCase> { GetGeoAddressUseCase(get()) }
        factory<UpdateDeliveryAddressUseCase> { UpdateDeliveryAddressUseCase(get()) }
        factory<GetDepartmentsUseCase> { GetDepartmentsUseCase(get(), get()) }
        factory<LoadCatalogUseCase> { LoadCatalogUseCase(get()) }
        factory<CreateOrderUseCase> { CreateOrderUseCase(get()) }
        factory<GetSbpBanksUseCase> { GetSbpBanksUseCase(get()) }
        factory<GetPaymentTypesUseCase> { GetPaymentTypesUseCase(get()) }
        factory<ClearCartUseCase> { ClearCartUseCase(get()) }
        factory<GetCurrentOrderUseCase> { GetCurrentOrderUseCase(get()) }
        factory<GetOrdersUseCase> { GetOrdersUseCase(get()) }
        factory<SearchAddressUseCase> { SearchAddressUseCase(get()) }
        factory<CreateCartUseCase> { CreateCartUseCase(get()) }
        factory<GetOrderByIdUseCase> { GetOrderByIdUseCase(get()) }
        factory<DeleteUserUseCase> { DeleteUserUseCase(get(), get(), get()) }
        factory<LogoutUserUseCase> { LogoutUserUseCase(get(), get(), get()) }
        factory<UpdateUserUseCase> { UpdateUserUseCase(get()) }
        factory { GetProductCardUseCase(get()) }

        // Mappers
        factory<AuthTypeMapper> { AuthTypeMapper() }
        factory<UserMapper> { UserMapper() }
        factory<CategoryMapper> { CategoryMapper(get()) }
        factory<ProductMapper> { ProductMapper() }
        factory<CartMapper> { CartMapper(get(), get(), get(), get(), get()) }
        factory<CartItemMapper> { CartItemMapper() }
        factory { CityMapper() }
        factory { DeliveryInfoMapper() }
        factory { AddressModelMapper(get()) }
        factory { GeoAddressMapper(get(), get()) }
        factory { DepartmentMapper(get(), get()) }
        factory { WorkingHoursMapper() }
        factory { OrderMapper(get(), get(), get()) }
        factory { OrderItemMapper() }
        factory { BankInfoMapper() }
        factory { PaymentMapper(get()) }
        factory { OrderUIModelMapper() }
        factory { TokenPairMapper() }

        single<HttpClient>(named("cart")) {
            val securityStorage: SecurityStorage = get()

            val httpClient = HttpClient {
                install(Logging) {
                    level = LogLevel.ALL // Уровень логирования (ALL, HEADERS, BODY, INFO, NONE)
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("Ktorfit Log: $message") // Логирование в консоль
                        }
                    }
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                        },
                    )
                }
                install(AuthPlugin()) {
                    tokenProvider = {
                        val token = securityStorage.getCartToken()
                        token
                    }
                }

                defaultRequest {
                    url(BuildTenantConfig.API_BASE_URL)
                }

//            install(HttpRequestRetry) {
//                retryOnServerErrors(maxRetries = 5)
//                exponentialDelay()
//            }
                expectSuccess = true
            }
            httpClient
        }

        single<HttpClient>(named("ws_orders")) {
            val securityStorage: SecurityStorage = get()
            val httpClient = HttpClient {
                install(Logging) {
                    level = LogLevel.ALL // Уровень логирования (ALL, HEADERS, BODY, INFO, NONE)
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("Ktorfit Log: $message") // Логирование в консоль
                        }
                    }
                }
                install(WebSockets) {
                    pingIntervalMillis = 20_000
                }
                install(AuthPlugin()) {
                    tokenProvider = {
                        val token = securityStorage.getAccessToken()
                        token
                    }
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                        },
                    )
                }
                expectSuccess = true
            }
            httpClient
        }

        single<HttpClient>(named("ws_callcheck")) {
            val httpClient = HttpClient {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("Ktorfit Log: $message")
                        }
                    }
                }
                install(WebSockets) {
                    pingIntervalMillis = 5_000
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                        },
                    )
                }
                expectSuccess = true
            }
            httpClient
        }

        single<HttpClient>(named("auth")) {
            val securityStorage: SecurityStorage = get()
            val authApi: AuthApi = get()

            val httpClient = HttpClient {
                install(Logging) {
                    level = LogLevel.ALL // Уровень логирования (ALL, HEADERS, BODY, INFO, NONE)
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("Ktorfit Log: $message") // Логирование в консоль
                        }
                    }
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                        },
                    )
                }
                install(AuthPlugin()) {
                    tokenProvider = {
                        val token = securityStorage.getAccessToken()
                        token
                    }
                }

                defaultRequest {
                    url {
                        if (isHttps) {
                            protocol = URLProtocol.HTTPS
                        }
                        host = BASE_URL
                    }
                }

//            install(HttpRequestRetry) {
//                retryOnServerErrors(maxRetries = 2)
//                exponentialDelay()
//            }
                expectSuccess = true
            }
            httpClient.plugin(HttpSend).intercept { request ->
                val originalCall = execute(request)

                if (originalCall.response.status.value == HttpStatusCode.Unauthorized.value) {
                    val refreshToken = securityStorage.getRefreshToken()
                    if (refreshToken.isNotEmpty()) {
                        try {
                            val refreshResponse = authApi.refreshTokens(RefreshTokenRequestBody(refreshToken))

                            if (refreshResponse.success && refreshResponse.tokens != null) {
                                securityStorage.saveAccessToken(refreshResponse.tokens.access)
                                securityStorage.saveRefreshToken(refreshResponse.tokens.refresh)
                                execute(request)
                            } else {
                                securityStorage.saveAccessToken("")
                                securityStorage.saveRefreshToken("")
                                throw HttpException.RequiredAuth
                            }
                        } catch (e: Exception) {
                            securityStorage.saveAccessToken("")
                            securityStorage.saveRefreshToken("")
                            throw e
                        }
                    } else {
                        throw IllegalStateException("No refresh token available")
                    }
                    execute(request)
                } else {
                    originalCall
                }
            }
            httpClient
        }

        single<HttpClient>(named("no_auth")) {
            val httpClient = HttpClient {
                install(Logging) {
                    level = LogLevel.ALL // Уровень логирования (ALL, HEADERS, BODY, INFO, NONE)
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("Ktorfit Log: $message") // Логирование в консоль
                        }
                    }
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                        },
                    )
                }

                defaultRequest {
                    url(BuildTenantConfig.API_BASE_URL)
                }

                expectSuccess = true
            }
            httpClient
        }

        // Api
        single<AuthApi> {
            val httpClient = get<HttpClient>(named("no_auth"))
            val wsClient = get<HttpClient>(named("ws_callcheck"))
            AuthApiImpl(httpClient, wsClient)
        }

        single<UserApi> {
            val httpClient = get<HttpClient>(named("auth"))
            UserApiImpl(httpClient)
        }

        single<CatalogApi> {
            val httpClient = get<HttpClient>(named("no_auth"))
            CatalogApiImpl(httpClient)
        }

        single<CartApi> {
            val httpClient = get<HttpClient>(named("cart"))
            CartApiImpl(httpClient)
        }

        single<MapApi> {
            val httpClient = get<HttpClient>(named("no_auth"))
            MapApiImpl(httpClient)
        }

        single<DepartmentApi> {
            val httpClient = get<HttpClient>(named("no_auth"))
            DepartmentApiImpl(httpClient)
        }

        single<PaymentApi> {
            val httpClient = get<HttpClient>(named("auth"))
            PaymentApiImpl(httpClient)
        }

        single<OrderApi> {
            val httpClient = get<HttpClient>(named("auth"))
            val wsClient = get<HttpClient>(named("ws_orders"))
            OrderApiImpl(httpClient, wsClient)
        }
    }