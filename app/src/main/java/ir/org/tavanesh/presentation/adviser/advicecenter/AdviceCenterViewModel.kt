package ir.org.tavanesh.presentation.adviser.advicecenter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviceCenterParams
import ir.org.tavanesh.core.domain.advisor.repository.GetAdvisersParams
import ir.org.tavanesh.core.domain.advisor.usecase.GetAdviceCenterUseCase
import ir.org.tavanesh.core.domain.advisor.usecase.GetAdvisersUseCase
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class AdviceCenterViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getAdvisersUseCase: GetAdvisersUseCase,
    private val getAdviceCenterUseCase: GetAdviceCenterUseCase,
) : BaseViewModel(viewUseCase) {

    var adviceCenter = MutableLiveData<AdviceCenter>()
    var adviceCenterId = ""
    fun getAdviceCenterInfo(item: AdviceCenter){
        adviceCenterId = item.id
        adviceCenter.postValue(item)

        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = getAdviceCenterUseCase.call(GetAdviceCenterParams(item.id))
            viewUseCase.setProgress(false)

            result.fold(
                success = {
                    adviceCenter.postValue(it)
                },
                failure = {
                    viewUseCase.setFailure(it)
                }
            )
        }
    }

    private  var adviserList = mutableListOf<Adviser>()
    var advisers = MutableLiveData<List<Adviser>>()
    private var page:Int = 1
    private var allDataFetched = false
    fun getAdvisers(){
        if(!allDataFetched){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getAdvisersUseCase.call(GetAdvisersParams(page, adviceCenterId))
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        adviserList.addAll(it)
                        advisers.postValue(adviserList)
                        page++
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