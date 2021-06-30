package com.superexercisebook.bugreport.okhttp.framework

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class KotlinHttpClient {
    fun test(s: String): String {
        val client = OkHttpClient()
        val url = "https://one.one.one.one/cdn-cgi/trace"
        val body: RequestBody = s.toRequestBody("plain/text; charset=utf-8".toMediaTypeOrNull())
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        client.newCall(request).execute().use { response ->
            return response.body!!.string()
        }
    }

}
