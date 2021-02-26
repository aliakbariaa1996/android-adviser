package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.entity.toUserStatus
import ir.org.tavanesh.data.models.UserResponse

fun UserResponse.toUser(): User {
    return User(
        age = this.age,
        education = this.education,
        gender = this.gender,
        email = this.email,
        firstName = this.firstName,
        id = this.id,
        lastName = this.lastName,
        mobileNumber = this.mobileNumber,
        nationalCode = this.nationalCode,
        status = this.status.toUserStatus(),
        username = this.username,
        avatar = this.avatar,
    )
}
