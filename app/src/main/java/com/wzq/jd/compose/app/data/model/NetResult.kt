package com.wzq.jd.compose.app.data.model

import kotlinx.serialization.Serializable

/**
 * create by wzq on 2023/11/27
 *
 */
@Serializable
data class NetResult<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)
//
//object KS: KSerializer<Any?> {
//    override val descriptor: SerialDescriptor
//        get() = TODO("Not yet implemented")
//
//    override fun deserialize(decoder: Decoder): Any? {
//        return decoder.dec
//    }
//
//    override fun serialize(encoder: Encoder, value: Any?) {
//    }
//
//
//}