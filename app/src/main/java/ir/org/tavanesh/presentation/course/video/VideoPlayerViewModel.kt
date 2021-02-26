package ir.org.tavanesh.presentation.course.video

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.PowerManager
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.children
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.question.entity.Answer
import ir.org.tavanesh.core.domain.question.entity.AnswerType
import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.domain.question.repository.AnswerQuestionParams
import ir.org.tavanesh.core.domain.question.usecase.AnswerQuestionUseCase
import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.domain.video.repository.GetVideoParams
import ir.org.tavanesh.core.domain.video.usecase.GetVideoUseCase
import ir.org.tavanesh.core.utils.Toast
import ir.org.tavanesh.data.platform.datasource.ViewUseCaseRepository
import ir.org.tavanesh.data.platform.model.PlayerStatus
import ir.org.tavanesh.presentation.base.BaseViewModel
import ir.org.tavanesh.presentation.base.widgets.checkBoxGenerator
import ir.org.tavanesh.presentation.base.widgets.radioButtonGenerator
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber

class VideoPlayerViewModel @ViewModelInject constructor(
    private val viewUseCase: ViewUseCaseRepository,
    private val getVideoUseCase: GetVideoUseCase,
    private val answerQuestionUseCase: AnswerQuestionUseCase,
    @ApplicationContext private val context: Context,
): BaseViewModel(viewUseCase) {

    var video: Video?=null
    private var isReady = false
    fun getVideo(videoId: String){
        if(!isReady){
            viewModelScope.launch {
                viewUseCase.setProgress(true)
                val result = getVideoUseCase.call(GetVideoParams(videoId))
                viewUseCase.setProgress(false)

                result.fold(
                    success = {
                        video = it
                        setPlayerState(PlayerStatus.START)
                        isReady = true
                    },
                    failure = {
                        viewUseCase.setFailure(it)
                    }
                )
            }
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    fun preventLock() {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock")
        wakeLock.acquire(20 * 60 * 1000L /*20 minutes*/)
        wakeLock.release()
    }

    var playerState = MutableLiveData<PlayerStatus>()
    fun setPlayerState(state: PlayerStatus) {
        playerState.postValue(state)
    }

    private var exoPlayer: SimpleExoPlayer? = null
    fun initVideoListener(exoPlayer: SimpleExoPlayer){
        this.exoPlayer = exoPlayer
        startTimer()
        isReady = true
    }

    private lateinit var radioLayout: RadioGroup
    private lateinit var checkLayout: LinearLayout
    private lateinit var edtInput: AppCompatEditText

    fun initViews(radioLayout: RadioGroup, checkLayout: LinearLayout, edtInput: AppCompatEditText){
        this.radioLayout = radioLayout
        this.checkLayout = checkLayout
        this.edtInput = edtInput
    }

    private fun isQuestionTime(second:Int){
        video?.let{
            var isTime = false
            it.questions?.forEach { question ->

                Timber.tag("TAG").d(
                    "second: $second \n question min to Show: ${question.secondToShow}"
                )
                if(question.secondToShow == second){
                    stopTimer()
                    setPlayerState(PlayerStatus.PAUSE)
                    setCurrentQuestion(question)
                    isTime = true
                }
            }
            if(!isTime){
                startTimer()
            }
        }
    }
    var isQuestionTime = MutableLiveData<Boolean>()
    var currentQuestion = MutableLiveData<Question>()
    fun promptAnswer(){
        viewModelScope.launch {
            currentQuestion.value?.let{ current ->
                var answer = Answer("","",current.id,false)
                when(current.type){
                    AnswerType.TEXT -> {
                        edtInput.text.toString().let {
                            if (it.isNotBlank()) {
                                answer.answer = it
                                answerQuestion(answer, current.type)
                            } else {
                                viewUseCase.alertUser(Toast(radioLayout.context.getString(R.string.error_not_answered)))
                            }
                        }
                    }
                    AnswerType.SINGLE_SELECT -> {
                        val rb: AppCompatRadioButton? =
                            radioLayout.findViewById(radioLayout.checkedRadioButtonId)
                        rb?.let {
                            //current.answers?.forEach { answer.isSelect = false }
                            answer = current.answers?.first { it.id == rb.tag }!!
                            answerQuestion(answer, current.type)
                        } ?: run {
                            viewUseCase.alertUser(Toast(radioLayout.context.getString(R.string.error_not_answered)))
                        }
                    }
                    AnswerType.MULTI_SELECT -> {
                        current.answers?.forEach { it.isSelect = false }
                        checkLayout.children.forEach { cb ->
                            if(cb is AppCompatCheckBox && cb.isChecked){
                                val a = current.answers?.first { it.id == cb.tag }!!
                                answer.id+="${a.id},"
                            }
                        }
                        if (answer.id!="")
                            answerQuestion(answer, current.type)
                        else
                            viewUseCase.alertUser(Toast(radioLayout.context.getString(R.string.error_not_answered)))
                    }
                }
            }
        }
    }
    private fun setCurrentQuestion(question:Question){
        viewModelScope.launch {
            question.let{
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
            currentQuestion.postValue(question)
            isQuestionTime.postValue(true)
        }
    }
    private fun answerQuestion(answer: Answer, type:AnswerType){
        viewModelScope.launch {
            viewUseCase.setProgress(true)
            val ob = JSONObject()
            try{
                ob.put("questionId",answer.questionId.toInt())
                ob.put("answerType",type.status)
                when(type){
                    AnswerType.TEXT ->{
                        ob.put("answer",answer.answer)
                    }
                    AnswerType.SINGLE_SELECT ->{
                        ob.put("answer" ,answer.id)
                    }
                    AnswerType.MULTI_SELECT ->{
                        val list = mutableListOf<Int>()
                        val a  = answer.answer.split(",")
                        a.forEach { item ->
                            if(item.isNotBlank()&& item.isNotEmpty()) list.add(item.toInt())
                        }
                        ob.put("answer",list)
                    }
                }
            }catch (e:JSONException){}


            val result = answerQuestionUseCase.call(AnswerQuestionParams(currentQuestion.value!!.id, ob.toString()))
            viewUseCase.setProgress(false)

            result.fold(
                success = {
                    isQuestionTime.postValue(false)
                    setPlayerState(PlayerStatus.PLAY)
                    startTimer()
                },
                failure = {
                    viewUseCase.setFailure(it)
                }
            )
        }
    }

    private val handler = Handler()
    private fun startTimer() {
        handler.postDelayed({
            val second = (exoPlayer!!.currentPosition / 1000).toInt()
            isQuestionTime(second)
        }, 1000)
    }
    fun stopTimer(){
        handler.removeCallbacksAndMessages(null)
    }

}