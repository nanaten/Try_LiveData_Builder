package com.nanaten.try_livedata_builder.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    companion object {
        private const val API_READ_TIMEOUT: Long = 10
        private const val API_CONNECT_TIMEOUT: Long = 10

        private fun getOkhttpClient(): OkHttpClient {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor {
                    val httpUrl = it.request().url
                    val requestBuilder = it.request().newBuilder().url(httpUrl)
                    it.proceed(requestBuilder.build())
                }
                .addInterceptor(logInterceptor)
                .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        }

        private fun getRetrofit(): Retrofit {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            return Retrofit.Builder()
                .client(getOkhttpClient())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        fun get(): GithubApi {
            val retrofit = getRetrofit()
            return retrofit.create(GithubApi::class.java)
        }
    }
}