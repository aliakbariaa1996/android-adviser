package ir.org.tavanesh.core.domain.exam.entity

import ir.org.tavanesh.core.domain.question.entity.Question
import java.io.Serializable

data class Exam(
    var id:String,
    var name:String,
    var description:String,
    var image:String,
    var questionCount:Int,
    var testTime:Int,
    var centerId:String,
    var questions:List<Question>?=null,
): Serializable

data class ExamResult(
    var description: String,
)

