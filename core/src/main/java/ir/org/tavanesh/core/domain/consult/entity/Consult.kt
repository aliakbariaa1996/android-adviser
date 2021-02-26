package ir.org.tavanesh.core.domain.consult.entity

import java.io.Serializable

data class Consult(
    var id:String,
    var title:String,
    var description:String,
    var centerName:String,
    var phone:String,
    var latitude:Double,
    var longitude:Double,
    var address:String,
    var adviserName:String,
    var status: ConsultStatues,
    var date:String,
    var time:String,
): Serializable

enum class ConsultStatues{
    ACTIVE_BOOKING,
    PAST
}