package ir.org.tavanesh.core.domain.video.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.domain.video.repository.GetVideoParams
import ir.org.tavanesh.core.domain.video.repository.VideoRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : Usecase<Video, GetVideoParams>() {
    override suspend fun call(
        params: GetVideoParams?
    ): Result<Video, Failure> {
        return params?.let {
            videoRepository.getVideo(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
