package com.app.lvif_front_end.models.login

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("_id") val userId: String? = null,
    @SerializedName("alias") val alias: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("lastName") val lastName: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("token") val token: String? = null,
)