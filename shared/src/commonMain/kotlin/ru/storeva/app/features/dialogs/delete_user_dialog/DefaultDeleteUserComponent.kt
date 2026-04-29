package ru.storeva.app.features.dialogs.delete_user_dialog

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.usecase.user.DeleteUserUseCase

class DefaultDeleteUserComponent(
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val callbacks: DeleteUserDialogCallbacks,
) : DeleteUserComponent(
        componentContext = componentContext,
        initialState = DeleteUserDialogViewState(false),
        reducer = DeleteUserDialogReducer(),
        snackBarManager = snackBarManager,
    ) {
    override fun onEvent(event: DeleteUserDialogViewEvent) {
        when (event) {
            DeleteUserDialogViewEvent.OnConfirm -> {
                confirm()
            }

            DeleteUserDialogViewEvent.OnDismiss -> {
                callbacks.onDismiss()
            }

            is DeleteUserDialogViewEvent.OnError -> {
                reduce(event)
                showError(event.error)
            }

            is DeleteUserDialogViewEvent.OnThrowError -> {
                reduce(event)
                showThrowError(event.throwable)
            }

            DeleteUserDialogViewEvent.OnLoading -> {
                reduce(event)
            }
        }
    }

    private fun confirm() {
        coroutineScope.launch {
            deleteUserUseCase
                .invoke(Unit)
                .catch {
                    withContext(Dispatchers.Main) {
                        onEvent(DeleteUserDialogViewEvent.OnThrowError(it))
                    }
                }.collect { resultModel ->
                    when (resultModel) {
                        is ResultModel.Error -> {
                            withContext(Dispatchers.Main) {
                                onEvent(DeleteUserDialogViewEvent.OnError(resultModel.message ?: "Что-то пошло не так"))
                            }
                        }

                        ResultModel.Loading -> {
                            withContext(Dispatchers.Main) {
                                onEvent(DeleteUserDialogViewEvent.OnLoading)
                            }
                        }

                        is ResultModel.Success<Boolean> -> {
                            if (resultModel.data) {
                                withContext(Dispatchers.Main) {
                                    callbacks.onSuccess()
                                }
                            }
                        }
                    }
                }
        }
    }
}