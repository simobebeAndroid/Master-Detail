package com.simiomobile.masterdetail.di.module

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val TIME_OUT: Long = 20L

open class BaseRemoteData {
    internal fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}