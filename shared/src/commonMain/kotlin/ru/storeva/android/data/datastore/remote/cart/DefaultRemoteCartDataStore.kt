package ru.storeva.android.data.datastore.remote.cart

import ru.storeva.android.data.api.auth_api.AuthApi
import ru.storeva.android.data.api.auth_api.model.CreateCartRequestBody
import ru.storeva.android.data.api.auth_api.model.CreateCartResponse
import ru.storeva.android.data.api.cart_api.CartApi
import ru.storeva.android.data.api.cart_api.model.GetCartResponseBody
import ru.storeva.android.data.api.cart_api.model.UpdateCartAddressRequestBody
import ru.storeva.android.data.api.cart_api.model.UpdateQuantityRequestBody

class DefaultRemoteCartDataStore(
    private val cartApi: CartApi,
    private val authApi: AuthApi,
) : RemoteCartDataStore {
    override suspend fun getCart(): GetCartResponseBody {
        return cartApi.getCart()
    }

    override suspend fun createCart(body: CreateCartRequestBody): CreateCartResponse {
        return authApi.createCart(body)
    }

    override suspend fun updateQuantity(
        productId: Long,
        quantity: Int,
    ): GetCartResponseBody {
        val request = UpdateQuantityRequestBody(productId, quantity)
        return cartApi.updateQuantity(request)
    }

    override suspend fun removeAll(): GetCartResponseBody {
        return cartApi.removeAll()
    }

    override suspend fun updateCartAddress(body: UpdateCartAddressRequestBody): GetCartResponseBody {
        return cartApi.updateAddress(body)
    }
}