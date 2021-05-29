package com.zohointerview.countrylist.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add Api token with every request
 */
class ApiKeyInterceptor(private val appId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var incomingRequest = chain.request()
        val interceptedUrl = incomingRequest.url().newBuilder().addQueryParameter("appid", appId)
            .build()
        incomingRequest = incomingRequest.newBuilder().url(interceptedUrl).build()
        return chain.proceed(incomingRequest)
    }

}
