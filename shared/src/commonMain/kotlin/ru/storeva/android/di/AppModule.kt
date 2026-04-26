package ru.storeva.android.di

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
import ru.storeva.android.data.HttpException
import ru.storeva.android.data.api.auth_api.AuthApi
import ru.storeva.android.data.api.auth_api.AuthApiImpl
import ru.storeva.android.data.api.auth_api.model.RefreshTokenRequestBody
import ru.storeva.android.data.api.cart_api.CartApi
import ru.storeva.android.data.api.cart_api.CartApiImpl
import ru.storeva.android.data.api.catalog.CatalogApi
import ru.storeva.android.data.api.catalog.CatalogApiImpl
import ru.storeva.android.data.api.departments_api.DepartmentApi
import ru.storeva.android.data.api.departments_api.DepartmentApiImpl
import ru.storeva.android.data.api.map_api.MapApi
import ru.storeva.android.data.api.map_api.MapApiImpl
import ru.storeva.android.data.api.order_api.OrderApi
import ru.storeva.android.data.api.order_api.OrderApiImpl
import ru.storeva.android.data.api.payment_api.PaymentApi
import ru.storeva.android.data.api.payment_api.PaymentApiImpl
import ru.storeva.android.data.api.security.AuthPlugin
import ru.storeva.android.data.api.user_api.UserApi
import ru.storeva.android.data.api.user_api.UserApiImpl
import ru.storeva.android.data.datastore.local.DefaultDepartmentLocalDataStore
import ru.storeva.android.data.datastore.local.DepartmentsLocalDataStore
import ru.storeva.android.data.datastore.local.SecurityStorage
import ru.storeva.android.data.datastore.local.catalog.DefaultLocalCatalogDataStore
import ru.storeva.android.data.datastore.local.catalog.LocalCatalogDataStore
import ru.storeva.android.data.datastore.local.user.DefaultUserLocalDataStore
import ru.storeva.android.data.datastore.local.user.UserLocalDataStore
import ru.storeva.android.data.datastore.remote.auth.AuthRemoteDataStore
import ru.storeva.android.data.datastore.remote.auth.DefaultAuthRemoteDatStore
import ru.storeva.android.data.datastore.remote.cart.DefaultRemoteCartDataStore
import ru.storeva.android.data.datastore.remote.cart.RemoteCartDataStore
import ru.storeva.android.data.datastore.remote.catalog.CatalogRemoteDataStore
import ru.storeva.android.data.datastore.remote.catalog.DefaultCatalogRemoteDataStore
import ru.storeva.android.data.datastore.remote.departments.DefaultDepartmentsRemoteDataStore
import ru.storeva.android.data.datastore.remote.departments.DepartmentsRemoteDataStore
import ru.storeva.android.data.datastore.remote.geo.DefaultGeoRemoteDatasource
import ru.storeva.android.data.datastore.remote.geo.GeoRemoteDatasource
import ru.storeva.android.data.datastore.remote.order.DefaultOrderRemoteDataStore
import ru.storeva.android.data.datastore.remote.order.OrderRemoteDataStore
import ru.storeva.android.data.datastore.remote.payment.DefaultPaymentRemoteDataStore
import ru.storeva.android.data.datastore.remote.payment.PaymentRemoteDataStore
import ru.storeva.android.data.datastore.remote.user.DefaultUserRemoteDatastore
import ru.storeva.android.data.datastore.remote.user.UserRemoteDataStore
import ru.storeva.android.data.mapper.AddressModelMapper
import ru.storeva.android.data.mapper.AuthTypeMapper
import ru.storeva.android.data.mapper.BankInfoMapper
import ru.storeva.android.data.mapper.CartItemMapper
import ru.storeva.android.data.mapper.CartMapper
import ru.storeva.android.data.mapper.CategoryMapper
import ru.storeva.android.data.mapper.CityMapper
import ru.storeva.android.data.mapper.DeliveryInfoMapper
import ru.storeva.android.data.mapper.DepartmentMapper
import ru.storeva.android.data.mapper.GeoAddressMapper
import ru.storeva.android.data.mapper.OrderItemMapper
import ru.storeva.android.data.mapper.OrderMapper
import ru.storeva.android.data.mapper.PaymentMapper
import ru.storeva.android.data.mapper.ProductMapper
import ru.storeva.android.data.mapper.TokenPairMapper
import ru.storeva.android.data.mapper.UserMapper
import ru.storeva.android.data.mapper.WorkingHoursMapper
import ru.storeva.android.data.repositories.auth.DefaultAuthRepository
import ru.storeva.android.data.repositories.cart.DefaultCartRepository
import ru.storeva.android.data.repositories.catalog.DefaultCatalogRepository
import ru.storeva.android.data.repositories.departments.DefaultDepartmentRepository
import ru.storeva.android.data.repositories.geo.DefaultGeoRepository
import ru.storeva.android.data.repositories.order.DefaultOrderRepository
import ru.storeva.android.data.repositories.payment.DefaultPaymentRepository
import ru.storeva.android.data.repositories.sbp_banks.DefaultSbpBanksRepository
import ru.storeva.android.data.repositories.token.DefaultTokenRepository
import ru.storeva.android.data.repositories.user.DefaultUserRepository
import ru.storeva.android.domain.repositories.AuthRepository
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.domain.repositories.CatalogRepository
import ru.storeva.android.domain.repositories.DepartmentsRepository
import ru.storeva.android.domain.repositories.GeoRepository
import ru.storeva.android.domain.repositories.OrderRepository
import ru.storeva.android.domain.repositories.PaymentRepository
import ru.storeva.android.domain.repositories.SbpBanksRepository
import ru.storeva.android.domain.repositories.TokenRepository
import ru.storeva.android.domain.repositories.UserRepository
import ru.storeva.android.domain.usecase.auth.GetAccessTokenUseCase
import ru.storeva.android.domain.usecase.auth.GetAuthTypesUseCase
import ru.storeva.android.domain.usecase.auth.VerifyCodeUseCase
import ru.storeva.android.domain.usecase.auth.VerifyPhoneNumberUseCase
import ru.storeva.android.domain.usecase.cart.AddToCartUseCase
import ru.storeva.android.domain.usecase.cart.ClearCartUseCase
import ru.storeva.android.domain.usecase.cart.CreateCartUseCase
import ru.storeva.android.domain.usecase.cart.LoadCartUseCase
import ru.storeva.android.domain.usecase.cart.RemoveFromCartUseCase
import ru.storeva.android.domain.usecase.cart.UpdateDeliveryAddressUseCase
import ru.storeva.android.domain.usecase.catalog.GetCategoriesUseCase
import ru.storeva.android.domain.usecase.catalog.GetProductCardUseCase
import ru.storeva.android.domain.usecase.catalog.GetProductsUseCase
import ru.storeva.android.domain.usecase.catalog.LoadCatalogUseCase
import ru.storeva.android.domain.usecase.departments.GetDepartmentsUseCase
import ru.storeva.android.domain.usecase.geo.GetGeoAddressUseCase
import ru.storeva.android.domain.usecase.geo.SearchAddressUseCase
import ru.storeva.android.domain.usecase.order.CreateOrderUseCase
import ru.storeva.android.domain.usecase.order.GetCurrentOrderUseCase
import ru.storeva.android.domain.usecase.order.GetOrderByIdUseCase
import ru.storeva.android.domain.usecase.order.GetOrdersUseCase
import ru.storeva.android.domain.usecase.payment.GetPaymentTypesUseCase
import ru.storeva.android.domain.usecase.sbp_banks.GetSbpBanksUseCase
import ru.storeva.android.domain.usecase.user.DeleteUserUseCase
import ru.storeva.android.domain.usecase.user.LoadUserUseCase
import ru.storeva.android.domain.usecase.user.LogoutUserUseCase
import ru.storeva.android.domain.usecase.user.UpdateUserUseCase
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.mapper.OrderUIModelMapper

// For IOS:
// private const val BASE_URL = "localhost:8080"

// For Android Studio
// private const val BASE_URL = "10.0.2.2:8080"
// For remote server
private const val BASE_URL = "foodbox-service-firkat.amvera.io"
private val isHttps = true

@OptIn(ExperimentalSerializationApi::class)
fun appModule() =
    module {
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
                    url {
                        if (isHttps) {
                            protocol = URLProtocol.HTTPS
                        }
                        host = BASE_URL
                    }
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
                    url {
                        if (isHttps) {
                            protocol = URLProtocol.HTTPS
                        }
                        host = BASE_URL
                    }
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