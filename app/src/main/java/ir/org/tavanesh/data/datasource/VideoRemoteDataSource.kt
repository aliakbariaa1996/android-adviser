package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.domain.video.repository.GetCourseVideosParams
import ir.org.tavanesh.core.domain.video.repository.GetVideoParams

interface VideoRemoteDataSource {
    suspend fun getCourseVideos(params: GetCourseVideosParams): List<Video>

    suspend fun getVideo(params: GetVideoParams): Video
}