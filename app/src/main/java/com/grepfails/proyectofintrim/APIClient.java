package com.grepfails.proyectofintrim;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grep on 12/01/2017.
 */

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
