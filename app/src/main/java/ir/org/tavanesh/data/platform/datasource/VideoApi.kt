package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.BaseResponse
import ir.org.tavanesh.data.models.VideoModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VideoApi {

    @GET("getEpisode")
    suspend fun getVideo(
        @Header("Authorization") token: String,
        @Query("episodeId") episodeId: String,
    ): NetworkResponse<BaseResponse<VideoModel>, String>

}