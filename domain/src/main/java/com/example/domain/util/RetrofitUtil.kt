package com.example.domain.util

import com.example.data.base.JSON
import com.example.domain.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitUtil {
    private const val CURRENCY_BASE_URL: String = "https://anyapi.io/api/v1/exchange/"
    private const val API_KEY = "fa19s7e6jtd5bkj38elh8ea6fdglia5muerqm4c3voj6vc0tpnpo"

    private const val OK_HTTP_CLIENT_CONNECT_TIMEOUT: Long = 30
    private const val OK_HTTP_CLIENT_READ_TIMEOUT: Long = 20

    private val logLevel: HttpLoggingInterceptor.Level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        .addInterceptor(AuthenticationInterceptor(API_KEY))
        .connectTimeout(OK_HTTP_CLIENT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(OK_HTTP_CLIENT_READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    private val jsonConverterFactory: Converter.Factory =
        JSON.asConverterFactory("application/json".toMediaType())

    fun create(baseUrl: String = CURRENCY_BASE_URL): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(jsonConverterFactory)
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }
}