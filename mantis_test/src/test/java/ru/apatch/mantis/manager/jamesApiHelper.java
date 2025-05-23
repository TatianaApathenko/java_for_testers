package ru.apatch.mantis.manager;


import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class jamesApiHelper extends HelperBase {

    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client;

    public jamesApiHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void addUser(String email, String password) {
        RequestBody body = RequestBody.create(
                String.format("{\"password\":\"%s\"}", password), JSON);
        Request request = new Request.Builder()
                .url(String.format("%S/users/%S", manager.property("james.apiBaseUrl"), email))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}