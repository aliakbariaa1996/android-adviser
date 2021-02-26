package ir.org.tavanesh.presentation.auth.verification

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.user.repository.CheckMobileParams
import ir.org.tavanesh.core.domain.user.repository.CheckVerificationCodeParams
import ir.org.tavanesh.core.domain.user.usecase.CheckMobileUseCase
import ir.org.tavanesh.core.domain.user.usecase.CheckVerificationCodeUseCase
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.data.platform.model.VerificationViews
import kotlinx.coroutines.launch

class VerificationViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val checkMobileUseCase: CheckMobileUseCase,
    private val verificationCodeUseCase: CheckVerificationCodeUseCase,
): ViewModel() {

    private lateinit var secretToken:String

    var viewState = MutableLiveData<VerificationViews>()
    fun changeState(state:VerificationViews){
        viewState.postValue(state)
    }

    fun checkMobileNumber(mobile:String) {
        var mobileParam = mobile
        if(mobile.isNotBlank() && mobile[0]=='0') mobileParam = mobile.substring(1,mobile.length)
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = checkMobileUseCase.call(CheckMobileParams(
                mobileParam
            ))

            viewUseCase.setProgress(false)
            result.fold(
                success = {
                    secretToken = it
                    changeState(VerificationViews.VERIFY_CODE)
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
                    viewUseCase.navigateUser(Navigate(
                        R.id.action_verificationFragment_to_registerFragment
                    ))
                },
                failure = {
                    viewUseCase.setFailure(it)
                },
            )
        }
    }

}