package com.example.weather.Retrofit;

import android.util.Base64;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {


    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }

        return retrofit;
    }
}
