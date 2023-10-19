package com.app.lvif_front_end.usecase.login

import com.app.lvif_front_end.api.ILvifApi
import com.app.lvif_front_end.models.login.UserModel
import com.app.lvif_front_end.models.template.ResponseApi
import com.app.lvif_front_end.room.entity.UserEntity
import com.app.lvif_front_end.room.service.UserService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val _api: ILvifApi,
    private val _service: UserService
) {
    private val _gson: Gson = Gson()
    fun login(
        username: String,
        password: String,
    ): Single<UserModel> =
        Single.create { emitter ->
            try {
                val response = _api.postRequest(
                    "auth/login",
                    mapOf("username" to username, "password" to password)
                ).execute()
                if (!response.isSuccessful) throw Exception();

                val resType = object : TypeToken<ResponseApi<UserModel>>() {}.type
                val resJson = _gson.toJson(response.body())
                val (statusCode, message, body) = _gson.fromJson<ResponseApi<UserModel>>(
                    resJson,
                    resType
                )

                val userEntity = UserEntity(
                    userId = body[0].userId!!,
                    alias = body[0].alias!!,
                    email = body[0].email!!,
                    image = body[0].image!!,
                    lastName = body[0].lastName!!,
                    name = body[0].name!!,
                    username = body[0].username!!,
                    token = body[0].token!!
                )

                _service.insert(userEntity)

                emitter.onSuccess(body[0])
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
        }
}