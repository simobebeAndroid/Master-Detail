package com.simiomobile.masterdetail.di.module

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val TIME_OUT: Long = 20L

open class BaseRemoteData {
    internal fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            getDefaultHttpLogin())
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
    private fun getDefaultHttpLogin(): HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY)
    class HttpLogger : HttpLoggingInterceptor.Logger {
        var TAG: String = this.javaClass.simpleName
        override fun log(message: String?) {
            val logName: String = "OkHttp"
            if (message!!.isEmpty()) {
                return
            } else if (!message.startsWith("{")) {
                return
            }
            var prettyPrintString: String = message

        }
    }
}