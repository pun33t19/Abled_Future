package com.example.abledfuture.service

import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class ApiService {

    var client: OkHttpClient = OkHttpClient();
    fun getRequest(sUrl: String): String? {
        var result: String? = null
        try {
            // Create URL
            val url = URL(sUrl)
            // Build request
            val request = Request.Builder().url(url).get()
                .addHeader("X-RapidAPI-Key", "685c2e89a0msh9e99f3168438145p19768cjsn104b3bbd9388")
                .addHeader("X-RapidAPI-Host", "jsearch.p.rapidapi.com")
                .build()
            // Execute request
            val response=client.newCall(request).execute()
            result = response.body?.string()
        } catch (err: Error) {
            print("Error when executing get request: " + err.localizedMessage)
        }
        return result
    }
}