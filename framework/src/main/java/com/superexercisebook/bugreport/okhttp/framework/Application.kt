package com.superexercisebook.bugreport.okhttp.framework

import okhttp3.MediaType

object Application {
    @JvmStatic
    fun main(args : Array<String>) {
        // Succeed
        println(JavaHttpClient().test("test"))

        // Success
        println(KotlinHttpClient().test("test"))

        // Blocked by @Deprecated annotation
        // MediaType.parse("plain/text; charset=utf-8")
    }
}
