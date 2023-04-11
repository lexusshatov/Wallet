package com.example.domain.util

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()
        val updatedRequest = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(updatedRequest)
    }
}