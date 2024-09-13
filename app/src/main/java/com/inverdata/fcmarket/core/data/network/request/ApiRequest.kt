package com.inverdata.fcmarket.core.data.network.request

sealed interface ApiRequest<out D, out E : Error> {
    data class Success<out D>(val data: D) : ApiRequest<D, Nothing>
    data class Failure<out E : Error>(val error: E) : ApiRequest<Nothing, E>
}

inline fun <T, E : Error, R> ApiRequest<T, E>.map(map: (T) -> R): ApiRequest<R, E> {
    return when (this) {
        is ApiRequest.Failure -> ApiRequest.Failure(error)
        is ApiRequest.Success -> ApiRequest.Success(map(data))
    }
}

fun <T, E : Error> ApiRequest<T, E>.asEmptyDataResult(): EmptyRequest<E> {
    return map {  }
}

inline fun <T, E: Error> ApiRequest<T, E>.onSuccess(action: (T) -> Unit): ApiRequest<T, E> {
    return when (this) {
        is ApiRequest.Failure -> this
        is ApiRequest.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E: Error> ApiRequest<T, E>.onFailure(action: (E) -> Unit): ApiRequest<T, E> {
    return when (this) {
        is ApiRequest.Failure -> {
            action(error)
            this
        }
        is ApiRequest.Success -> this
    }
}


typealias EmptyRequest<E> = ApiRequest<Unit, E>