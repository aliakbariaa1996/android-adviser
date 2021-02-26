package ir.org.tavanesh.presentation.base.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.GetUserParams
import ir.org.tavanesh.core.domain.user.repository.LogoutUserParams
import ir.org.tavanesh.core.domain.user.usecase.GetUserUseCase
import ir.org.tavanesh.core.domain.user.usecase.LogoutUserUseCase
import ir.org.tavanesh.core.utils.Dialog
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val getUserUseCase: GetUserUseCase,
): ViewModel() {

    fun provideProgress() = viewUseCase.provideProgress()
    fun provideAlert() = viewUseCase.provideAlert()
    fun provideNavigation() = viewUseCase.provideNavigation()
    fun provideToolbar() = viewUseCase.provideToolbar()
    fun provideFailure() = viewUseCase.provideFailure()
    fun provideKeyboardCloseRequest() = viewUseCase.provideKeyboardCloseRequest()

    fun logout(){
        viewModelScope.launch {
            val result = logoutUserUseCase.call(LogoutUserParams())
            result.fold(
                success = {
                    viewUseCase.navigateUser(Navigate(
                        R.id.splashFragment
                    ))
                },
                failure = {}
            )
        }
    }

    var user = MutableLiveData<User>()
    fun getUserInfo() {
        viewModelScope.launch {

            val result = getUserUseCase.call(GetUserParams())
            result.fold(
                success = {
                    user.postValue(it)
                },
                failure = {
                    viewUseCase.setFailure(it)
                }
            )
        }
    }

    fun navigateTo(destinationId: Int, bundle: Bundle? = null) {
        viewUseCase.navigateUser(
            Navigate(
                destinationId,
                bundle
            )
        )
    }

    fun showDialog(message: String) {
        viewUseCase.alertUser(Dialog(message))
    }

}