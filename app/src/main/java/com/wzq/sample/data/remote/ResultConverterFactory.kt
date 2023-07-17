package com.wzq.sample.data.remote

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * create by wzq on 2023/4/3
 * TODO NOT WORK
 */
class ResultConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter<ResponseBody, Result<*>> { body ->
            try {
                val result = delegate.convert(body)
                Result.success(result)
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    }
}