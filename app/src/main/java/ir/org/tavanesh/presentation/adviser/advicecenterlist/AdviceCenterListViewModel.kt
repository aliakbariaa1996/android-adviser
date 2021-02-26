package ir.org.tavanesh.presentation.adviser.advicecenterlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviceCentersParams
import ir.org.tavanesh.core.domain.advisor.usecase.GetAdviceCentersUseCase
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class AdviceCenterListViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getAdviceCentersUseCase: GetAdviceCentersUseCase,
) : BaseViewModel(viewUseCase) {

    private var params = GetAdviceCentersParams(1, 0, 0)
    fun setParams(value:GetAdviceCentersParams){
        params = value
        cityName.postValue(params.text)
    }
    var cityName = MutableLiveData<String>()

    private var adviceCenterList = mutableListOf<AdviceCenter>()
    var adviceCenters = MutableLiveData<List<AdviceCenter>>()

    private var allDataFetched = false
    fun getAdviceCenters(){
        if(!allDataFetched) {
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getAdviceCentersUseCase.call(params)
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        adviceCenterList.addAll(it)
                        adviceCenters.postValue(adviceCenterList)
                        params.page++
                        if(it.isEmpty()) allDataFetched = true
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
    }


}