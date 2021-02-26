package ir.org.tavanesh.presentation.exam.show

import android.os.Bundle
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.presentation.base.BaseViewModel

class ExamShowViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository
) : BaseViewModel(viewUseCase) {

    var exam = MutableLiveData<Exam>()

    fun getExam(value:Exam){
        exam.postValue(value)
    }

    fun startExam(){
        val bundle = Bundle()
        bundle.putSerializable(OBJECT, exam.value)
        viewUseCase.navigateUser(Navigate(
            R.id.action_examShowFragment_to_takeExamFragment, bundle
        ))
    }

}