package com.wzq.jetpack.data.remote

/**
 * This class can be a return type.
 *
 * @see [Result]
 */
class Snapshot<out T>(private val value: Any?) {
    companion object {
        fun <T> success(value: T): Snapshot<T> = Snapshot(value)
        fun <T> failure(exception: Throwable): Snapshot<T> = Snapshot(Error(exception))


        suspend fun <T> runCatch(block: suspend () -> T): Snapshot<T> =
            try {
                success(block())
            } catch (exception: Throwable) {
                failure(exception)
            }
    }

    fun isSuccess() = value !is Error
    fun isFailure() = value is Error

    fun getOrNull(): T? = when (value) {
        isFailure() -> null
        else -> value as? T
    }

    data class Error(
        val exception: Throwable
    )
}