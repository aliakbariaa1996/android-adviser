package ir.org.tavanesh.data.impl.datasource

import android.content.SharedPreferences
import ir.org.tavanesh.core.utils.*
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): AuthLocalDataSource {
    override suspend fun getToken(): String {
        return withContext(Dispatchers.IO) {
            sharedPreferences.getString(TOKEN, "") ?: ""
        }
    }

    override suspend fun setToken(token: String): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                putString(TOKEN, "Bearer $token")
            }.commit()
        }
    }

    override suspend fun logout(): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                putString(TOKEN, "")
                putBoolean(IS_INTRO_WATCHED, false)
                putBoolean(IS_FIRST_EXAM_TAKE, false)
                putBoolean(IS_WALK_THROUGH_WATCHED, false)
            }.commit()
        }
    }

    override suspend fun getFireBaseToken(): String {
        return withContext(Dispatchers.IO) {
            sharedPreferences.getString(FIREBASE_TOKEN, "") ?: ""
        }
    }

    override suspend fun setFireBaseToken(token: String): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                putString(FIREBASE_TOKEN, token)
            }.commit()
        }
    }

}