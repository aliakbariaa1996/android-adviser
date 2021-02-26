package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.domain.video.repository.GetCourseVideosParams
import ir.org.tavanesh.core.domain.video.repository.GetVideoParams
import ir.org.tavanesh.core.domain.video.repository.VideoRepository
import ir.org.tavanesh.core.utils.DEFAULT_VIDEO_URL
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.VideoRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoRemoteDataSource: VideoRemoteDataSource,
): VideoRepository {
    override suspend fun getCourseVideos(params: GetCourseVideosParams): Result<List<Video>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = videoRemoteDataSource.getCourseVideos(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getVideo(params: GetVideoParams): Result<Video, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = videoRemoteDataSource.getVideo(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }
}
