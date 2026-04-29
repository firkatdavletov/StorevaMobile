package ru.storeva.app.data.datastore.remote.cart

import ru.storeva.app.data.api.auth_api.model.CreateCartRequestBody
import ru.storeva.app.data.api.auth_api.model.CreateCartResponse
import ru.storeva.app.data.api.cart_api.model.GetCartResponseBody
import ru.storeva.app.data.api.cart_api.model.UpdateCartAddressRequestBody

interface RemoteCartDataStore {
    suspend fun getCart(): GetCartResponseBody

    suspend fun createCart(body: CreateCartRequestBody): CreateCartResponse

    suspend fun updateQuantity(
        productId: Long,
        quantity: Int,
    ): GetCartResponseBody

    suspend fun removeAll(): GetCartResponseBody

    suspend fun updateCartAddress(body: UpdateCartAddressRequestBody): GetCartResponseBody
}