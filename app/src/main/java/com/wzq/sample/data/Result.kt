//package com.wzq.sample.data
//
///**
// * A generic class that holds a value with its loading status.
// * @param <T>
// */
//sealed class Result<out T : Any> {
//
//    companion object {
//         inline fun <T: Any> runCatch(block:  () -> T): Result<T> {
//            return try {
//                Success(block())
//            } catch (e: Exception) {
//                Error(e)
//            }
//        }
//    }
//
//    data class Success<out T : Any>(val data: T) : Result<T>()
//    data class Error(val exception: Exception) : Result<Nothing>()
//
//    override fun toString(): String {
//        return when (this) {
//            is Success<*> -> "Success[data=$data]"
//            is Error -> "Error[exception=$exception]"
//        }
//    }
//
//    fun getOrNull(): T? {
//        return if (this is Success) {
//            this.data
//        } else {
//            null
//        }
//    }
//
//    fun getOrThrow(): T{
//        return (this as Success).data
//    }
//}