package ir.org.tavanesh.core.domain.user.entity

data class User(
    var age: String?=null,
    var education: String?=null,
    var gender: String?=null,
    var email: String?=null,
    var firstName: String?=null,
    var id: String?=null,
    var lastName: String?=null,
    var mobileNumber: String?=null,
    var nationalCode: String?=null,
    var status: UserStatus?= UserStatus.BAN,
    var username: String?=null,
    var avatar: String = "",
)

