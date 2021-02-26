package ir.org.tavanesh.core.domain.advisor.entity

data class AdviserTime(
    var id:String,
    var time:String,
    var weekday:String,
    var isSelected:Boolean = false,
)