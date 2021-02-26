package ir.org.tavanesh.presentation.adviser.adviser

import android.os.Bundle
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviserFreeTimesByDataParams
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviserParams
import ir.org.tavanesh.core.domain.advisor.usecase.GetAdviserFreeTimesByDateUseCase
import ir.org.tavanesh.core.domain.advisor.usecase.GetAdviserUseCase
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.SubmitConsultingParams
import ir.org.tavanesh.core.domain.consult.usecase.SubmitConsultUseCase
import ir.org.tavanesh.core.utils.InvalidInputFailure
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.data.platform.model.AdviserViews
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class AdviserViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getAdviserUseCase: GetAdviserUseCase,
    private val getAdviserFreeTimesByDateUseCase: GetAdviserFreeTimesByDateUseCase,
    private val submitConsultUseCase: SubmitConsultUseCase,
) : BaseViewModel(viewUseCase) {

    var viewState = MutableLiveData<AdviserViews>()
    fun setViewState(state:AdviserViews){
        viewState.postValue(state)
    }

    fun setAdviceCenterId(centerId:String){
        submitParams.centerId = centerId
    }

    var adviser = MutableLiveData<Adviser>()
    fun getAdviserInfo(item: Adviser){
        adviser.postValue(item)
        submitParams.adviserId = item.id

        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = getAdviserUseCase.call(GetAdviserParams(item.id))
            viewUseCase.setProgress(false)

            result.fold(
                success = {
                    adviser.postValue(it)
                },
                failure = {
                    viewUseCase.setFailure(it)
                }
            )
        }
    }

    var isDatePicked = false
    var freeTimeList = mutableListOf<AdviserTime>()
    private fun getAdviserFreeTimes(){
        if(freeTimeList.isEmpty()){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getAdviserFreeTimesByDateUseCase.call(
                    GetAdviserFreeTimesByDataParams(submitParams.adviserId, submitParams.date)
                )
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        isDatePicked = true
                        freeTimeList.clear()
                        freeTimeList.addAll(it)
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
    }

    var showableDate = MutableLiveData<String>()
    var showableTime = MutableLiveData<String>()

    fun setDate(persianDate: String, gregorianDate: String) {
        submitParams.date = gregorianDate.replace('/', '-')
        showableDate.postValue(persianDate)
        getAdviserFreeTimes()
    }
    fun setTime(adviserTime: AdviserTime){
        submitParams.timeId = adviserTime.id
        showableTime.postValue(adviserTime.time)
    }

    private  var submitParams = SubmitConsultingParams("","","","")
    var consult = MutableLiveData<Consult>()
    fun submitConsultant(){
        if(submitParams.date.isNotEmpty() && submitParams.timeId.isNotEmpty()){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = submitConsultUseCase.call(submitParams)
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        consult.postValue(it)
                        setViewState(AdviserViews.DONE)
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }else{
            viewUseCase.setFailure(InvalidInputFailure())
        }
    }
    fun goToConsultInfo(){
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, consult.value)
        viewUseCase.navigateUser(Navigate(
            R.id.action_adviserFragment_to_consultInfoFragment,
            bundle
        ))
    }

}