package ir.org.tavanesh.presentation.auth.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.user.entity.UserAppUsage
import ir.org.tavanesh.core.domain.user.entity.UserStatus
import ir.org.tavanesh.core.domain.user.repository.GetUserAppUsageParams
import ir.org.tavanesh.core.domain.user.repository.GetUserParams
import ir.org.tavanesh.core.domain.user.usecase.GetUserAppUsageUseCase
import ir.org.tavanesh.core.domain.user.usecase.GetUserUseCase
import ir.org.tavanesh.core.utils.BanFailure
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getUserAppUsageUseCase: GetUserAppUsageUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    fun checkUserAuthStatus() {
        /*val nav = Navigate(
            R.id.action_splashFragment_to_dashboardFragment,
        )
        navigateUser(nav)*/
        viewModelScope.launch {

            var userAppUsage:UserAppUsage? = null
            val result = getUserAppUsageUseCase.call(
                GetUserAppUsageParams()
            )
            result.fold(
                success = {
                    userAppUsage = it
                },
                failure = {
                    val nav = Navigate(
                        R.id.action_splashFragment_to_authMenuFragment,
                    )
                    navigateUser(nav)
                }
            )
            userAppUsage?.let{

                Timber.d("share token: ${it.token}")
                Timber.d("share firebaseToken: ${it.firebaseToken}")

                if (!it.isIntroWatched) {
                    val nav = Navigate(
                        R.id.action_splashFragment_to_introFragment,
                    )
                    navigateUser(nav)
                } else {
                    if (it.token == "") {
                        val nav = Navigate(
                            R.id.action_splashFragment_to_authMenuFragment,
                        )
                        navigateUser(nav)

                    } else {
                        val user = getUserUseCase.call(GetUserParams())
                        user.fold(
                            success = {
                                when(it.status){
                                    UserStatus.ACTIVE ->{
                                        val nav = Navigate(
                                            R.id.action_splashFragment_to_dashboardFragment,
                                        )
                                        navigateUser(nav)
                                    }
                                    UserStatus.BAN ->{
                                        viewUseCase.setFailure(BanFailure())
                                    }
                                    UserStatus.REGISTER ->{
                                        val nav = Navigate(
                                            R.id.action_splashFragment_to_registerFragment,
                                        )
                                        navigateUser(nav)
                                    }
                                    UserStatus.QUESTIONNAIRE ->{
                                        val nav = Navigate(
                                            R.id.action_splashFragment_to_examListFragment,
                                        )
                                        navigateUser(nav)
                                    }
                                }
                            },
                            failure = { failure->
                                //todo: show another view state and show button
                                viewUseCase.setFailure(failure)
                                val nav = Navigate(
                                    R.id.action_splashFragment_to_authMenuFragment,
                                )
                                navigateUser(nav)
                            }
                        )
                    }
                }
            }


        }
    }

    private fun navigateUser(nav: Navigate) {
        viewUseCase.navigateUser(nav)
    }
}