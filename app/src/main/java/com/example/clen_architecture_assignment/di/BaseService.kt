package com.example.clen_architecture_assignment.di

import com.example.clen_architecture_assignment.BuildConfig
import com.example.clen_architecture_assignment.data.remote.LoginApiCall
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class BaseService {
    private var retrofitBaseApi: Retrofit? = null

    fun getBaseApi(): LoginApiCall{
        return createRetrofitBase().create(LoginApiCall::class.java)
    }
    private fun createRetrofitBase(): Retrofit {
        if (retrofitBaseApi == null) {
            retrofitBaseApi = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()

        }
        return retrofitBaseApi as Retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val originalRequest: Request = chain.request()


            val requestBuilder = originalRequest.newBuilder()

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}