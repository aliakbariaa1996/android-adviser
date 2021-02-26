package ir.org.tavanesh.core.domain.video.repository

data class GetCourseVideosParams(
    var courseId: String? = null,
)

data class GetVideoParams(
    var episodeId: String,
)