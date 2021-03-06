package com.diiser.network.config

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = " https://deezerdevs-deezer.p.rapidapi.com"

val apiService: Api by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)
}
//    .addInterceptor(ChuckInterceptor(context))

private fun provideOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor { chain ->
            val request = chain.request()
            chain.proceed(
                request.newBuilder()
                    .addHeader(
                        "x-rapidapi-host",
                        "deezerdevs-deezer.p.rapidapi.com"
                    ).addHeader(
                        "x-rapidapi-key", "e39d59c328mshc259ec0bca8a2c0p114221jsn712cd6596548"
                    ).build()
            )
        }.build()
}

