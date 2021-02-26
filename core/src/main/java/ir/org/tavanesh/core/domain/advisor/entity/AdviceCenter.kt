package ir.org.tavanesh.core.domain.advisor.entity

import java.io.Serializable

data class AdviceCenter(
    var id:String,
    var name:String,
    var description:String,
    var image:String,
    var address:String,
    var adviserCount:Int,
    var latitude:Double,
    var longitude:Double,
): Serializable
