package com.app.lvif_front_end.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.lvif_front_end.room.entity.UserEntity


/**
 * metodos para interactuar con la base de datos
 * */
@Dao
interface UserDao {
    @Query("select * from user")
    fun getAllUsers(): List<UserEntity>

    @Query("select * from user where userId = :userId")
    fun getUserById(userId: String): UserEntity

    @Insert
    fun insertUser(user: UserEntity);

    // no recomendado, se pierde toda la información
    @Query("delete from user")
    fun deleteAllUsers()
}