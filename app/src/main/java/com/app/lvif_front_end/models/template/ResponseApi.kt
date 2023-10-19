package com.app.lvif_front_end.models.template

import com.google.gson.annotations.SerializedName

data class Message(
    val errorCode: String,
    val message: String
)

data class ResponseApi<T>(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: List<Message>,
    @SerializedName("body")
    val body: List<T>
)
