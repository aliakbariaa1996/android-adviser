package ir.org.tavanesh.presentation.auth.intro

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.user.repository.UpdateUserAppUsageParams
import ir.org.tavanesh.core.domain.user.usecase.UpdateUserAppUsageUseCase
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.data.enitity.Intro
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import kotlinx.coroutines.launch

class IntroViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val updateUserAppUsageUseCase: UpdateUserAppUsageUseCase,
) : ViewModel() {
    private var pagerPosition = 0

    fun setPagerPosition(pos: Int) {
        pagerPosition = pos
    }

    private var pager = MutableLiveData<Int>()
    fun providePager() = pager

    fun onNextIntroPageClicked() {
        if (pagerPosition == 2) {
            viewModelScope.launch {
                updateUserAppUsageUseCase.call(
                    UpdateUserAppUsageParams(isIntroWatched = true)
                )
                viewUseCase.navigateUser(
                    Navigate(
                        R.id.action_introFragment_to_authMenuFragment,
                    )
                )
            }
        } else {

            pager.value = pagerPosition + 1
        }
    }

    fun generateItems(context: Context): List<Intro> {
        val introList = mutableListOf<Intro>()

        introList.add(
            Intro(
                context.getString(R.string.intro_one),
                R.drawable.asset_one
            )
        )
        introList.add(
            Intro(
                context.getString(R.string.intro_two),
                R.drawable.asset_three
            )
        )
        introList.add(
            Intro(
                context.getString(R.string.intro_three),
                R.drawable.asset_two
            )
        )

        return introList


    }
}