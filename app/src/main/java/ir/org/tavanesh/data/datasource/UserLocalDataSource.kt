package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.user.entity.UserAppUsage
import ir.org.tavanesh.core.domain.user.repository.GetUserAppUsageParams

interface UserLocalDataSource {
    suspend fun getUserAppUsage(params: GetUserAppUsageParams): UserAppUsage

    suspend fun setIsIntroWatched(params: Boolean): Boolean

    suspend fun setIsFirstExamTake(params: Boolean): Boolean
}