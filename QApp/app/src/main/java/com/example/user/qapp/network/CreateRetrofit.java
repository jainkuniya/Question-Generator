package com.example.user.qapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 10/4/18.
 */

public class CreateRetrofit {
    public Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8000/").addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
