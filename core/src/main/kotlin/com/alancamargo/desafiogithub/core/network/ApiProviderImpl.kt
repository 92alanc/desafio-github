package com.alancamargo.desafiogithub.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val TIMEOUT_SECONDS = 10L

internal class ApiProviderImpl @Inject constructor(
    private val baseUrl: String
) : ApiProvider {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    override fun <T> provideService(clazz: Class<T>): T {
        val converterFactory = getConverterFactory()
        val client = getClient()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create(clazz)
    }

    private fun getConverterFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    private fun getClient(): OkHttpClient {
        val loggingInterceptor = getLoggingInterceptor()

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
