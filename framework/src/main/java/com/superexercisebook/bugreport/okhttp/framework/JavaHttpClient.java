package com.superexercisebook.bugreport.okhttp.framework;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;


public class JavaHttpClient {
    public String test(String s) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://one.one.one.one/cdn-cgi/trace";
        RequestBody body = RequestBody.create(MediaType.parse("plain/text; charset=utf-8"), s);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        try {
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
