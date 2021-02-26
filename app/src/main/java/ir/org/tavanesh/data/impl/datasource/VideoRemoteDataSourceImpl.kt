package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.video.entity.Video
import ir.org.tavanesh.core.domain.video.repository.GetCourseVideosParams
import ir.org.tavanesh.core.domain.video.repository.GetVideoParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.VideoRemoteDataSource
import ir.org.tavanesh.data.mappers.toCourse
import ir.org.tavanesh.data.mappers.toVideo
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import ir.org.tavanesh.data.platform.datasource.VideoApi
import javax.inject.Inject

class VideoRemoteDataSourceImpl @Inject constructor(
    private val videoApi: VideoApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
) : VideoRemoteDataSource {
    override suspend fun getCourseVideos(params: GetCourseVideosParams): List<Video> {
        return mutableListOf()
    }

    override suspend fun getVideo(params: GetVideoParams): Video {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                videoApi.getVideo(
                    token, params.episodeId,
                ).waitResponse {
                    it.toVideo()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }
}