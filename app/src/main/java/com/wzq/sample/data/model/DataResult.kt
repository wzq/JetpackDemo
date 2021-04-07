package com.wzq.sample.data.model

/**
 * create by wzq on 2021/4/6
 *
 */

@Suppress("UNCHECKED_CAST")
open class DataResult<out T>(val data: T?) {

    val errorCode: Int = 0
    val errorMsg: String = ""

    companion object {

        suspend fun <T> catch(block: suspend () -> DataResult<T>): DataResult<T> {
            return try {
                block()
            } catch (ex: Exception) {
                error(ex)
            }
        }

        fun <T> success(value: T): DataResult<T> {
            return DataResult(value)
        }

        fun <T> error(value: Exception): DataResult<T> {
            return DataResult(null)
        }

        fun createErrorResult(value: Exception){
            Result.Error(value)
        }
    }

    val isSuccess: Boolean get() = data !is Exception


    val isFailure: Boolean get() = data is Exception


    fun getOrNull(): T? {
        return when {
            isFailure -> null
            else -> data
        }
    }

    fun getOrThrow(): T {
        if (data is Exception){
            throw data
        }
        return data as T
    }


    data class Error(
        val ex: Exception
    )
}
