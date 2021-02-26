package ir.org.tavanesh.core.domain.user.entity

data class UserAppUsage(
    val isIntroWatched:Boolean,
    val isFirstExamTake:Boolean,
    val token:String,
    val firebaseToken:String,
)