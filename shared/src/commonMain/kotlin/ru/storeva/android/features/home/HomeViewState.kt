package ru.storeva.android.features.home

import ru.storeva.android.domain.models.CategoryModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.DepartmentModel
import ru.storeva.android.features.base.Reducer

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