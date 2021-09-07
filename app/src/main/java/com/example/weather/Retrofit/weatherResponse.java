package com.example.weather.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class weatherResponse {
    @SerializedName("weather")
    public ArrayList<Weather> weathers = new ArrayList<Weather>();
    @SerializedName("main")
    public Main main;
    @SerializedName("name")
    public String name;

}
