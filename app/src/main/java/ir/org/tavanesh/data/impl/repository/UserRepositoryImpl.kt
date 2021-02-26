package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.entity.UserAppUsage
import ir.org.tavanesh.core.domain.user.repository.*
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.UserLocalDataSource
import ir.org.tavanesh.data.datasource.UserRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
): UserRepository {
    override suspend fun getUser(params: GetUserParams): Result<User, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = userRemoteDataSource.getUser(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getUserAppUsage(params: GetUserAppUsageParams): Result<UserAppUsage, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = userLocalDataSource.getUserAppUsage(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun updateUserAppUsage(params: UpdateUserAppUsageParams): Result<Boolean, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                params.isIntroWatched?.let{
                    userLocalDataSource.setIsIntroWatched(it)
                }
                params.isFirstExamTake?.let{
                    userLocalDataSource.setIsFirstExamTake(it)
                }
                params.token?.let{
                    authLocalDataSource.setToken(it)
                }
                params.firebaseToken?.let{
                    authLocalDataSource.setFireBaseToken(it)
                }
                Result.success(true)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun updateUser(params: UpdateUserParams): Result<Boolean, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = userRemoteDataSource.updateUser(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

}