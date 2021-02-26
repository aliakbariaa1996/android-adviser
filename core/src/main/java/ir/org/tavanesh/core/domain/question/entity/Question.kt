package ir.org.tavanesh.core.domain.question.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Question(
    var id:String,
    var question:String,
    var answers:List<Answer>? = null,
    var isRequired:Boolean,
    var type:AnswerType,
    var secondToShow:Int?=null,
    var answer: String="",
):Serializable

data class Answer(
    var id:String,
    var answer:String,
    var questionId:String,
    var isSelect:Boolean=false
):Serializable

enum class AnswerType(val status: String) : Serializable {
    @SerializedName("MULTI_SELECT")
    MULTI_SELECT("MULTI_SELECT"),

    @SerializedName("SINGLE_SELECT")
    SINGLE_SELECT("SINGLE_SELECT"),

    @SerializedName("TEXT")
    TEXT("TEXT"),
}