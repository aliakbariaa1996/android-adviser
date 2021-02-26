package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.GetUserParams
import ir.org.tavanesh.core.domain.user.repository.UpdateUserParams

interface UserRemoteDataSource {
    suspend fun getUser(params: GetUserParams): User

    suspend fun updateUser(params: UpdateUserParams): Boolean
}