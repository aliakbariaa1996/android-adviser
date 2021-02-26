package ir.org.tavanesh.presentation.auth.forget

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.user.repository.CheckVerificationCodeParams
import ir.org.tavanesh.core.domain.user.repository.ForgetPasswordParams
import ir.org.tavanesh.core.domain.user.repository.SetPasswordParams
import ir.org.tavanesh.core.domain.user.usecase.CheckVerificationCodeUseCase
import ir.org.tavanesh.core.domain.user.usecase.ForgetPasswordUseCase
import ir.org.tavanesh.core.domain.user.usecase.SetPasswordUseCase
import ir.org.tavanesh.core.utils.InvalidInputFailure
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.data.platform.model.ForgetPasswordViews
import kotlinx.coroutines.launch

class ForgetPasswordViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val verificationCodeUseCase: CheckVerificationCodeUseCase,
    private val forgetPasswordUseCase: ForgetPasswordUseCase,
    private val setPasswordUseCase: SetPasswordUseCase,
): ViewModel() {

    private lateinit var secretToken:String

    var viewState = MutableLiveData<ForgetPasswordViews>()
    fun changeState(state: ForgetPasswordViews){
        viewState.postValue(state)
    }

    fun forgetPassword(mobile:String) {
        var mobileParam = mobile
        if(mobile.isNotBlank() && mobile[0]=='0') mobileParam = mobile.substring(1,mobile.length)
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = forgetPasswordUseCase.call(
                ForgetPasswordParams(mobileParam)
            )

            viewUseCase.setProgress(false)
            result.fold(
                success = {
                    secretToken = it
                    changeState(ForgetPasswordViews.VERIFY_CODE)
                },
                failure = {
                    viewUseCase.setFailure(it)
                },
            )
        }
    }

    fun checkVerifyCode(verifyCode:String) {
        viewUseCase.closeKeyboard()
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = verificationCodeUseCase.call(
                CheckVerificationCodeParams(
                    secretToken,
                    verifyCode
                )
            )

            viewUseCase.setProgress(false)
            result.fold(
                success = {
                    changeState(ForgetPasswordViews.PASSWORD)
                },
                failure = {
                    viewUseCase.setFailure(it)
                },
            )
        }
    }

    fun setPassword(password:String, rePassword:String){
        if(password.isNotBlank() && password.length>6 && password == rePassword){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = setPasswordUseCase.call(SetPasswordParams(
                    password,
                ))
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        viewUseCase.navigateUser(Navigate(
                            R.id.action_forgetPasswordFragment_to_dashboardFragment
                        ))
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    },
                )
            }
        }
        else{
            viewUseCase.setFailure(InvalidInputFailure())
        }
    }

}