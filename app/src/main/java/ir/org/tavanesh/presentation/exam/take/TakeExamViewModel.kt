package ir.org.tavanesh.presentation.exam.take

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.children
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.AnswerExamParams
import ir.org.tavanesh.core.domain.exam.usecase.AnswerExamUseCase
import ir.org.tavanesh.core.domain.question.entity.AnswerType
import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.domain.question.repository.GetQuestionListParams
import ir.org.tavanesh.core.domain.question.usecase.GetQuestionListUseCase
import ir.org.tavanesh.core.utils.Navigate
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.Toast
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.data.platform.model.CurrentQuestion
import ir.org.tavanesh.presentation.base.BaseViewModel
import ir.org.tavanesh.presentation.base.widgets.checkBoxGenerator
import ir.org.tavanesh.presentation.base.widgets.radioButtonGenerator
import kotlinx.coroutines.launch

class TakeExamViewModel@ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val answerExamUseCase: AnswerExamUseCase,
) : BaseViewModel(viewUseCase) {

    val exam = MutableLiveData<Exam>()
    var currentQuestion = MutableLiveData<CurrentQuestion>()

    private val questionList = mutableListOf<Question>()

    private lateinit var radioLayout: RadioGroup
    private lateinit var checkLayout:LinearLayout
    private lateinit var edtInput:AppCompatEditText

    fun initViews(radioLayout: RadioGroup, checkLayout:LinearLayout, edtInput:AppCompatEditText){
        this.radioLayout = radioLayout
        this.checkLayout = checkLayout
        this.edtInput = edtInput
    }

    private var examId = ""
    fun setExam(value: Exam){
        examId = value.id
        exam.postValue(value)
    }

    private var isFirst = true
    fun getQuestions(){
        if(isFirst){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getQuestionListUseCase.call(
                    GetQuestionListParams(examId)
                )
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        questionList.addAll(it)
                        nextQuestion()
                        isFirst = false
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
    }

    fun previewsQuestion(){
        currentQuestion.value?.index?.let{
            if(it!=0){
                val previewsIndex = it-1
                setCurrentQuestion(previewsIndex)
            }
        }
    }

    private fun nextQuestion(){
        val nextIndex = currentQuestion.value?.let{it.index+1}?:run{0}

        if(nextIndex<questionList.size){
            setCurrentQuestion(nextIndex)
        }
        else{
            answerExam()
        }
    }

    private fun setCurrentQuestion(index:Int){
        viewModelScope.launch {
            questionList[index].let{
                when (it.type) {
                    AnswerType.MULTI_SELECT -> {
                        checkBoxGenerator(checkLayout, it.answers!!)
                    }
                    AnswerType.SINGLE_SELECT -> {
                        radioButtonGenerator(radioLayout, it.answers!!)
                    }
                    AnswerType.TEXT -> {
                        edtInput.setText(it.answer)
                    }
                }
            }
            currentQuestion.postValue(
                CurrentQuestion(
                index, questionList[index]
            )
            )
        }
    }

    fun answerQuestion(){
        viewModelScope.launch {
            currentQuestion.value?.let{ current ->
                when(current.question?.type){
                    AnswerType.TEXT -> {
                        edtInput.text.toString().let {
                            if(it.isNotBlank()){
                                questionList[current.index].answer = it
                                nextQuestion()
                            }
                            else{
                                viewUseCase.alertUser(Toast(radioLayout.context.getString(R.string.error_not_answered)))
                            }
                        }
                    }
                    AnswerType.SINGLE_SELECT -> {
                        val rb: AppCompatRadioButton? = radioLayout.findViewById(radioLayout.checkedRadioButtonId)
                        rb?.let{
                            questionList[current.index].answers?.forEach { it.isSelect = false }
                            questionList[current.index].answers?.first { it.id == rb.tag }?.isSelect = true
                            nextQuestion()
                        }?:run{
                            viewUseCase.alertUser(Toast(radioLayout.context.getString(R.string.error_not_answered)))
                        }
                    }
                    AnswerType.MULTI_SELECT ->{
                        var isAnySelect = false
                        questionList[current.index].answers?.forEach { it.isSelect = false }
                        checkLayout.children.forEach { cb ->
                            if(cb is AppCompatCheckBox && cb.isChecked){
                                isAnySelect = true
                                questionList[current.index].answers?.first { it.id == cb.tag }?.isSelect = true
                            }
                        }
                        if(isAnySelect)
                            nextQuestion()
                        else
                            viewUseCase.alertUser(Toast(radioLayout.context.getString(R.string.error_not_answered)))
                    }
                }
            }
        }
    }

    private fun answerExam(){
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val result = answerExamUseCase.call(
                AnswerExamParams(exam.value!!.id, questionList)
            )
            viewUseCase.setProgress(false)

            result.fold(
                success = {
                    val bundle = Bundle()
                    bundle.putString(OBJECT, it)
                    viewUseCase.navigateUser(Navigate(
                        R.id.action_takeExamFragment_to_examResultFragment,
                        bundle
                    ))
                },
                failure = {
                    viewUseCase.setFailure(it)
                },
            )
        }
    }

}