package ir.org.tavanesh.data.impl.datasource

import android.content.SharedPreferences
import ir.org.tavanesh.core.domain.user.entity.UserAppUsage
import ir.org.tavanesh.core.domain.user.repository.GetUserAppUsageParams
import ir.org.tavanesh.core.utils.FIREBASE_TOKEN
import ir.org.tavanesh.core.utils.IS_FIRST_EXAM_TAKE
import ir.org.tavanesh.core.utils.IS_INTRO_WATCHED
import ir.org.tavanesh.core.utils.TOKEN
import ir.org.tavanesh.data.datasource.UserLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): UserLocalDataSource {
    override suspend fun getUserAppUsage(params: GetUserAppUsageParams): UserAppUsage {
        return withContext(Dispatchers.IO) {
            val token = sharedPreferences.getString(TOKEN, "") ?: ""
            val fbToken = sharedPreferences.getString(FIREBASE_TOKEN, "") ?: ""
            val isIntroWatched = sharedPreferences.getBoolean(IS_INTRO_WATCHED, false)
            val isFirstExamTake = sharedPreferences.getBoolean(IS_FIRST_EXAM_TAKE, false)
            UserAppUsage(
                isIntroWatched = isIntroWatched,
                isFirstExamTake = isFirstExamTake,
                token = token,
                firebaseToken = fbToken,
            )
        }
    }

    override suspend fun setIsIntroWatched(params: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                putBoolean(IS_INTRO_WATCHED, params)
            }.commit()
        }
    }

    override suspend fun setIsFirstExamTake(params: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                putBoolean(IS_FIRST_EXAM_TAKE, params)
            }.commit()
        }
    }
}