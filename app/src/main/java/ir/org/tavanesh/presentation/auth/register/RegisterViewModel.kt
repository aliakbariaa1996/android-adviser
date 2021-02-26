package ir.org.tavanesh.presentation.auth.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.user.entity.Configs
import ir.org.tavanesh.core.domain.user.entity.Education
import ir.org.tavanesh.core.domain.user.entity.Gender
import ir.org.tavanesh.core.domain.user.repository.GetConfigsParams
import ir.org.tavanesh.core.domain.user.repository.RegisterUserParams
import ir.org.tavanesh.core.domain.user.usecase.GetConfigsUseCase
import ir.org.tavanesh.core.domain.user.usecase.RegisterUserUseCase
import ir.org.tavanesh.core.utils.InvalidInputFailure
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.ext.isValidInt
import ir.org.tavanesh.ext.isValidString
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(
    private val getConfigsUseCase: GetConfigsUseCase,
    private val viewUseCase: ViewUseCaseRepository,
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    var configs = MutableLiveData<Configs>()
    private var registerParams = RegisterUserParams()

    fun getConfigs(){
        viewModelScope.launch {
            val result = getConfigsUseCase.call(GetConfigsParams())
            result.fold(
                success = {
                    configs.postValue(it)
                },
                failure = {
                    viewUseCase.setFailure(it)
                }
            )
        }
    }

    fun register(
        name: String, family: String, age: String, email: String, password: String,
        rePassword: String, nationalCode: String, username: String,
    ) {

        registerParams.firstName = name
        registerParams.lastName = family
        registerParams.age = age.toIntOrNull()
        registerParams.nationalCode = nationalCode
        registerParams.email = email
        registerParams.password = password
        registerParams.username = username

        if(isValidToReg() && password == rePassword){
            viewModelScope.launch {
                val result = registerUserUseCase.call(registerParams)
                result.fold(
                    success = {
                        viewUseCase.navigateUser(Navigate(
                            R.id.action_registerFragment_to_examListFragment
                        ))
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
        else{
            viewUseCase.setFailure(InvalidInputFailure())
        }

    }

    fun setGender(gender: Gender){
        registerParams.gender= gender.key
    }
    fun setEducation(education: Education){
        registerParams.education = education.key
    }

    private fun isValidToReg():Boolean{
        return (
            registerParams.firstName.isValidString()
            && registerParams.firstName.isValidString()
            && registerParams.gender.isValidString()
            && registerParams.age.isValidInt()
            && registerParams.education.isValidString()
            && registerParams.lastName.isValidString()
            && registerParams.username.isValidString()
            && registerParams.nationalCode.isValidString()
        )
    }

}