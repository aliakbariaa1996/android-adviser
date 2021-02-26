package ir.org.tavanesh.presentation.exam.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.GetExamListParams
import ir.org.tavanesh.core.domain.exam.usecase.GetExamListUseCase
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ExamListViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getExamListUseCase: GetExamListUseCase,
) : BaseViewModel(viewUseCase) {

    private var examList = mutableListOf<Exam>()
    var exams = MutableLiveData<List<Exam>>()
    private var page:Int = 1
    private var allDataFetched = false

    fun getExamList() {
        if(!allDataFetched) {
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getExamListUseCase.call(GetExamListParams(page))
                viewUseCase.setProgress(false)
                result.fold(
                    success = {
                        examList.addAll(it)
                        exams.postValue(examList)
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