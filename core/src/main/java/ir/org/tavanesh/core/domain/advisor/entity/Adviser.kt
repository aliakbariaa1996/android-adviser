package ir.org.tavanesh.core.domain.advisor.entity

import java.io.Serializable

data class Adviser(
    var id: String,
    var lastName:String,
    var firstName:String,
    var description:String,
    var avatar:String,
):Serializable{
    fun fullName():String{
        return "${this.firstName} ${this.lastName}"
    }
}
