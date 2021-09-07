package com.example.weather.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
//https://api.openweathermap.org/data/2.5/

    @GET("data/2.5/weather?&appid=4bb179af14d678f9a22a86a2426858d1&units=imperial")
    Call<weatherResponse> getData(@Query("q") String name);



    @GET("data/2.5/weather?&appid=4bb179af14d678f9a22a86a2426858d1&units=imperial")
    Call<weatherResponse> getDataZip(@Query("zip") String zip);



}
