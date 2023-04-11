package com.example.myapplication

import android.content.Context
import com.example.myapplication.Utility.MyPreferences
import com.example.myapplication.remote.RestApi
import com.example.myapplication.remote.RestApiHelper
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun okHttpClient() = run {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    fun provideBaseUrl() = "https://portal-qa.ebest-iot.com/VendingAppApi/"

    @Provides
    @Singleton
    fun retrofitObject(okHttpClient: OkHttpClient, url: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient())
        .baseUrl(url)
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RestApi = RestApiHelper(retrofit)

    @Provides
    @Singleton
    fun provideContext(): Context {
        return BaseApplication.appContext
    }

    @Provides
    @Singleton
    fun myPreference(): MyPreferences=MyPreferences(provideContext())

}