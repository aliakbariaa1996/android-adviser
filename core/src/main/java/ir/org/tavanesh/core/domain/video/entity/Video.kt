package ir.org.tavanesh.core.domain.video.entity

import ir.org.tavanesh.core.domain.question.entity.Question
import java.io.Serializable

data class Video(
    var id:String,
    var name:String,
    var description:String,
    var videoUrl:String,
    var questions:List<Question>?= mutableListOf(),
):Serializable