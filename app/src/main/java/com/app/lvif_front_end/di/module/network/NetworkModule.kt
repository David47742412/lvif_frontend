package com.app.lvif_front_end.di.module.network

import com.app.lvif_front_end.api.ILvifApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_HTTP_URL = "https://lvif-api.lldevll.xyz/api/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_HTTP_URL).addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
                    //.addInterceptor()
                    .build()
            ).build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ILvifApi = retrofit.create(ILvifApi::class.java)
}