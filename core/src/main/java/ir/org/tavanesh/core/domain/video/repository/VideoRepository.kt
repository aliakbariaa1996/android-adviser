package ir.org.tavanesh.core.domain.video.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.utils.Failure

interface VideoRepository {
    suspend fun getCourseVideos(params: GetCourseVideosParams): Result<List<Video>, Failure>

    suspend fun getVideo(params: GetVideoParams): Result<Video, Failure>
}