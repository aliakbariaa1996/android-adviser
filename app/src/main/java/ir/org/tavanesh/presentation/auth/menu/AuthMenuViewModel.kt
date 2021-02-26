package ir.org.tavanesh.presentation.auth.menu

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import ir.org.tavanesh.R
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository

class AuthMenuViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
) : ViewModel() {

    fun setStateRegisterForm(){
        viewUseCase.navigateUser(
            Navigate(
                R.id.action_authMenuFragment_to_verificationFragment,
            )
        )
    }

    fun goToLoginPage(){
        viewUseCase.navigateUser(
            Navigate(
                R.id.action_authMenuFragment_to_loginFragment,
            )
        )
    }
}