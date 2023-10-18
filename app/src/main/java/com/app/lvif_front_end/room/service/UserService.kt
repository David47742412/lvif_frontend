package com.app.lvif_front_end.room.service

import com.app.lvif_front_end.room.dao.UserDao
import com.app.lvif_front_end.room.entity.UserEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserService @Inject constructor(
    private val _userDao: UserDao
) {

    fun getAllUser(): Single<List<UserEntity>> {
        return Single.create { emitter ->
            try {
                emitter.onSuccess(_userDao.getAllUsers())
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
        }
    }

}