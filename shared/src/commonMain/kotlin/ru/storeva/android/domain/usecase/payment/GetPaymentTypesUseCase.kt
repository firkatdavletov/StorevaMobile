package ru.storeva.android.domain.usecase.payment

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.PaymentTypeModel
import ru.storeva.android.domain.repositories.PaymentRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetPaymentTypesUseCase(
    private val paymentRepository: PaymentRepository,
) : IOUseCase<Unit, List<PaymentTypeModel>>() {
    override fun execute(param: Unit): Flow<List<PaymentTypeModel>> {
        return paymentRepository.getPaymentTypes()
    }
}