package ir.org.tavanesh.core.domain.video.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.domain.video.repository.GetCourseVideosParams
import ir.org.tavanesh.core.domain.video.repository.VideoRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetCourseVideosUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : Usecase<List<Video>, GetCourseVideosParams>() {
    override suspend fun call(
        params: GetCourseVideosParams?
    ): Result<List<Video>, Failure> {
        return params?.let {
            videoRepository.getCourseVideos(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
