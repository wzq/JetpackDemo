package com.wzq.app2.data.model

/**
 * create by wzq on 2021/4/6
 *
 */
sealed class DataResult<out T : Any> {

    companion object {
        suspend fun <T : Any> catch(block: suspend () -> DataResult<T>): DataResult<T> {
            return try {
                block()
            } catch (ex: Exception) {
                ex.printStackTrace()
                Error(ex)
            }
        }
    }

    val errorCode: Int = 0
    val errorMsg: String = ""

    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }

    fun getOrNull(): Success<T>? {
        return when (this) {
            is Success -> this
            is Error -> null
        }
    }

}