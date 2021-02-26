package ir.org.tavanesh.core.domain.course.repository

data class GetCourseListParams(
    var page: Int = 0,
)

data class GetCourseParams(
    var courseId: String,
)
