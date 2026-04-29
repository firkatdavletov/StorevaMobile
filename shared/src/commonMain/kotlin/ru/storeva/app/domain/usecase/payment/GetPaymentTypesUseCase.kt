package ru.storeva.app.domain.usecase.payment

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.PaymentTypeModel
import ru.storeva.app.domain.repositories.PaymentRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetPaymentTypesUseCase(
    private val paymentRepository: PaymentRepository,
) : IOUseCase<Unit, List<PaymentTypeModel>>() {
    override fun execute(param: Unit): Flow<List<PaymentTypeModel>> {
        return paymentRepository.getPaymentTypes()
    }
}