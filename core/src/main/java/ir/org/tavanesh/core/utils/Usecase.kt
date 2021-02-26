package ir.org.tavanesh.core.utils

import com.github.kittinunf.result.Result as Either

abstract class Usecase<T, P> {
    abstract suspend fun call(params: P? = null): Either<T, Failure>
}
