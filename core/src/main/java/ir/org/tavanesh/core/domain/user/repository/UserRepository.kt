package ir.org.tavanesh.core.domain.user.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.UserAppUsage
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.utils.Failure

interface UserRepository {
    suspend fun getUser(params: GetUserParams): Result<User, Failure>

    suspend fun getUserAppUsage(params: GetUserAppUsageParams): Result<UserAppUsage, Failure>

    suspend fun updateUserAppUsage(params: UpdateUserAppUsageParams): Result<Boolean, Failure>

    suspend fun updateUser(params: UpdateUserParams): Result<Boolean, Failure>
}
