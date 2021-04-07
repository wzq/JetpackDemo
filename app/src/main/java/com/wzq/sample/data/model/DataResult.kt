package com.wzq.sample.data.model

import kotlin.NullPointerException

/**
 * create by wzq on 2021/4/6
 *
 */

@Suppress("UNCHECKED_CAST")
class DataResult<out T>(val data: T? = null) {

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

        fun error(ex: Exception): DataResult<Nothing> {
            val data = DataResult<Nothing>()
            data.failure = Failure(ex)
            return data
        }

    }

    var failure: Failure? = null

    val isFailure: Boolean get() = failure != null

    fun getOrNull(): T? {
        return when {
            isFailure -> null
            else -> data
        }
    }

    fun getOrThrow(): T {
        if (isFailure) {
            throw failure!!.ex
        } else if (data == null) {
            throw NullPointerException("data is null")
        }
        return data
    }


    data class Failure(
        val ex: Exception
    )
}
