package com.example.base



sealed class MyResponse<out R> {

    object None : MyResponse<Nothing>()
    data class Success<out T>(val data: T) : MyResponse<T>()
    data class Error(val error: Exception) : MyResponse<Nothing>()
    data class Loading(val loading: Boolean) : MyResponse<Nothing>()
    object Canceled : MyResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is Loading -> "Loading"
            Canceled -> "Canceled"
            None -> "None"
        }
    }

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error
    fun isCanceled() = this is Canceled
}


inline fun <R> MyResponse<R>.onLoading(action: (Boolean) -> Unit): MyResponse<R> {
    if (this is MyResponse.Loading) {
        action(true)
    } else if (this !is Nothing) action(false)
    return this
}

inline fun <T> MyResponse<T>.onError(onFailure: (Exception) -> Unit) {
    if (this is MyResponse.Error) onFailure(error)
}


inline fun <R> MyResponse<R>.onSuccess(action: (R) -> Unit): MyResponse<R> {
    if (this is MyResponse.Success) {
        action(data)
    }
    return this
}

fun <R> MyResponse<R>.getDataOrException(): MyResponse.Success<R> {
    return (this as MyResponse.Success)
}

fun <T> MyResponse<T>.successOr(fallback: T): T {
    return (this as? MyResponse.Success<T>)?.data ?: fallback
}

suspend fun <T> MyResponse<T>.merge(action: suspend () -> Unit): MyResponse<T> {
    let {
        it.onSuccess {
            action.invoke()
        }
        it.onError { error ->
            throw error
        }
    }
    return this
}


