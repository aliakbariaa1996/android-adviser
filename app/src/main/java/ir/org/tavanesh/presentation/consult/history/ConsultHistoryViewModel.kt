package ir.org.tavanesh.presentation.consult.history

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingHistoryParams
import ir.org.tavanesh.core.domain.consult.usecase.GetConsultingHistoryUseCase
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.domain.course.repository.GetCourseListParams
import ir.org.tavanesh.core.domain.exam.usecase.GetExamListUseCase
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ConsultHistoryViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getConsultingHistoryUseCase: GetConsultingHistoryUseCase,
) : BaseViewModel(viewUseCase) {

    private var consultList = mutableListOf<Consult>()
    var consults = MutableLiveData<List<Consult>>()
    private var page: Int = 1
    private var allDataFetched = false
    fun getConsultHistory() {
        if(!allDataFetched){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getConsultingHistoryUseCase.call(GetConsultingHistoryParams(page))
                viewUseCase.setProgress(false)
                result.fold(
                    success = {
                        consultList.addAll(it)
                        consults.postValue(consultList)
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