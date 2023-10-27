package com.app.lvif_front_end.models.book

import com.app.lvif_front_end.models.user.UserModel
import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("_id")
    val bookId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("user")
    val user: UserModel
)