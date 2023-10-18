package com.app.lvif_front_end.room.entity

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

/**
 * entidad que representa al usuario en la base de datos del lado del cliente
 * */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val userId: String,

    @ColumnInfo(name = "alias")
    val alias: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "username")
    val username: String
)
