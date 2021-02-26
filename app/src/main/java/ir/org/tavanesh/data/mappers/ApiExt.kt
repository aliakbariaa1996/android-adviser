package ir.org.tavanesh.data.mappers

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.core.utils.*
import ir.org.tavanesh.data.models.BaseResponse
import kotlinx.coroutines.*

suspend fun <T, R> NetworkResponse<BaseResponse<T>, Any>.waitResponse(
    mapper: (T) -> R
): R {

    this.let { response ->
        return withContext(Dispatchers.IO) {
            when (response) {
                is NetworkResponse.Success<*> -> {
                    try {
                        mapper((response.body as BaseResponse<T>).data)
                    } catch (e: Exception) {
                        throw UnKnownFailure()
                    }
                }
                is NetworkResponse.ServerError<*> -> {
                    try {
                        //val error: String = (response.body as String)
                        if (response.code == server_auth || response.code == server_auth_2) {
                            throw AuthFailure()
                        } else {
                            throw ResponseFailure()
                        }
                    } catch (e: Exception) {
                        if(e is Failure) throw  e
                        else throw ResponseFailure()
                    }
                }
                else -> {
                    throw ServerFailure()
                }
            }
        }
    }
}
