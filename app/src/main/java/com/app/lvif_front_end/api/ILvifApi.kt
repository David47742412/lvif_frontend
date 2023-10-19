package com.app.lvif_front_end.api

import androidx.room.Delete
import com.app.lvif_front_end.models.login.UserModel
import com.app.lvif_front_end.models.template.ResponseApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface ILvifApi {

    @GET
    fun getRequest(@Url url: String): Call<ResponseApi<Any>>;

    @POST
    fun postRequest(@Url url: String, @Body body: Any): Call<ResponseApi<Any>>

    @PUT
    fun putRequest(@Url url: String, @Body body: Any): Call<ResponseApi<Any>>

    @Delete
    fun deleteRequest(@Url url: String, @Body body: Any?): Call<ResponseApi<Any>>

}