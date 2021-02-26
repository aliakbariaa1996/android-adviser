package ir.org.tavanesh.core.domain.course.entity

import android.content.Context
import ir.org.tavanesh.core.R
import ir.org.tavanesh.core.domain.video.entity.Video
import java.io.Serializable

data class Course(
    var id:String,
    var name:String,
    var teacherName:String,
    var timeCost:String,
    var backgroundColor:String,
    var imageUrl:String,
    var description:String,
    var rate: Float = 5f,
    var videos:List<Video>?= mutableListOf()
    ):Serializable {

    fun getBackground(context:Context):Int{
        when(this.backgroundColor){
            "one"-> return context.resources.getColor(R.color.one)
            "two"-> return context.resources.getColor(R.color.two)
            "three"-> return context.resources.getColor(R.color.three)
            "four"-> return context.resources.getColor(R.color.four)
            "five"-> return context.resources.getColor(R.color.five)
            "six"-> return context.resources.getColor(R.color.six)
            "seven"-> return context.resources.getColor(R.color.seven)
            "eight"-> return context.resources.getColor(R.color.eight)
            "nine"-> return context.resources.getColor(R.color.nine)
            "ten"-> return context.resources.getColor(R.color.ten)
            "eleven"-> return context.resources.getColor(R.color.eleven)
            "twelve"-> return context.resources.getColor(R.color.twelve)
            "thirteen"-> return context.resources.getColor(R.color.thirteen)
            "fourteen"-> return context.resources.getColor(R.color.fourteen)
            "fifteen"-> return context.resources.getColor(R.color.fifteen)
            "sixteen"-> return context.resources.getColor(R.color.sixteen)
            "seventeen"-> return context.resources.getColor(R.color.seventeen)
            "eighteen"-> return context.resources.getColor(R.color.eighteen)
            else -> return context.resources.getColor(R.color.eighteen)
        }
    }
}

