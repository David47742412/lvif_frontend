package com.app.lvif_front_end.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.lvif_front_end.room.dao.UserDao
import com.app.lvif_front_end.room.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class LvifDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}