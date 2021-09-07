package com.example.weather.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    public Double temp;

    @SerializedName("humidity")
    public String humidity;

    /*
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    */

}
