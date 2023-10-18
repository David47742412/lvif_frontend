package com.app.lvif_front_end.di.module

import android.content.Context
import androidx.room.Room
import com.app.lvif_front_end.room.dao.UserDao
import com.app.lvif_front_end.room.database.LvifDatabase
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideLvifDatabase(@ApplicationContext context: Context): LvifDatabase =
        Room.databaseBuilder(
            context,
            LvifDatabase::class.java,
            "lvif_main"
        ).build()

    @Provides
    @Singleton
    fun provideUserDao(lvifDatabase: LvifDatabase): UserDao =
        lvifDatabase.getUserDao()

}