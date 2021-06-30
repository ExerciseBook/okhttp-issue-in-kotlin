package com.superexercisebook.bugreport.okhttp.project

import com.superexercisebook.bugreport.okhttp.framework.JavaHttpClient
import com.superexercisebook.bugreport.okhttp.framework.KotlinHttpClient
import okhttp3.MediaType

object Application {
    @JvmStatic
    fun main(args : Array<String>) {
        // Succeed
        println(JavaHttpClient().test("test"))

        // Failed
        println(KotlinHttpClient().test("test"))

        // Succeed
        MediaType.parse("plain/text; charset=utf-8")
    }
}
