package ru.storeva.app.features.home

import ru.storeva.app.domain.models.CategoryModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.DepartmentModel
import ru.storeva.app.features.base.Reducer

data class HomeViewState(
    val userName: String?,
    val categories: List<CategoryModel>,
    val currentOrders: List<OrderUIModel>,
    val freeDeliveryPrice: Long?,
    val productsPrice: Long,
    val amount: Long,
    val deliveryType: DeliveryType,
    val deliveryPrice: Long,
    val deliveryAddress: String,
    val cartDepartment: DepartmentModel?,
    val storeIsClosed: Boolean,
) : Reducer.ViewState