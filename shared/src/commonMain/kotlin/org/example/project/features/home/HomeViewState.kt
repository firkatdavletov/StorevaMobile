package org.example.project.features.home

import org.example.project.domain.models.CategoryModel
import org.example.project.domain.models.DeliveryType
import org.example.project.domain.models.DepartmentModel
import org.example.project.features.base.Reducer

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