package ir.org.tavanesh.presentation.auth.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.user.entity.UserStatus
import ir.org.tavanesh.core.domain.user.repository.LoginUserParams
import ir.org.tavanesh.core.domain.user.usecase.LoginUserUseCase
import ir.org.tavanesh.core.utils.BanFailure
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val loginUserUseCase: LoginUserUseCase,
) : ViewModel() {


    fun login(username:String, password:String){
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = loginUserUseCase.call(LoginUserParams(
                username, password
            ))
            viewUseCase.setProgress(false)

            result.fold(
                success = { user ->
                    when (user.status) {
                        UserStatus.QUESTIONNAIRE -> {
                            viewUseCase.navigateUser(
                                Navigate(
                                    R.id.action_loginFragment_to_examListFragment,
                                )
                            )
                        }
                        UserStatus.ACTIVE -> {
                            viewUseCase.navigateUser(
                                Navigate(
                                    R.id.action_loginFragment_to_dashboardFragment,
                                )
                            )
                        }
                        UserStatus.BAN -> {
                            viewUseCase.setFailure(BanFailure())
                        }
                        UserStatus.REGISTER -> {
                            viewUseCase.navigateUser(
                                Navigate(
                                    R.id.action_loginFragment_to_registerFragment,
                                )
                            )
                        }

                    }

                },
                failure = {
                    viewUseCase.setFailure(it)
                },
            )
        }
    }

    fun forgetPassword(){
        viewUseCase.navigateUser(
            Navigate(
                R.id.action_loginFragment_to_forgetPasswordFragment,
            )
        )
    }

}