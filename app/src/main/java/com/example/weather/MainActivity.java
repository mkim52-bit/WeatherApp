package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weather.Retrofit.APIClient;
import com.example.weather.Retrofit.ApiInterface;
import com.example.weather.Retrofit.Weather;
import com.example.weather.Retrofit.weatherResponse;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText search;
    TextView temp,humid,location,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search =  findViewById(R.id.search);
        temp =  findViewById(R.id.temp);
        humid = findViewById(R.id.humidity);
        location = findViewById(R.id.location);
        desc = findViewById(R.id.desc);




        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    String input = search.getText().toString().trim();
                    if(isNumeric(input)){
                        Log.d("insideNum","num");
                        getWeatherDataZip(input);

                    }
                    else{
                        Log.d("insideStr","str");
                        getWeatherData(input);
                    }

                  closeKeyboard();
                }

                return false;
            }
        });

    }
    public void getWeatherData(String name){
        Log.d("beforeAPI","before");
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Log.d("beforeCALL","before");
        Call<weatherResponse> call = service.getData(name);
        Log.d("beforeENQ","before");
        call.enqueue(new Callback<weatherResponse>() {
            @Override
            public void onResponse(Call<weatherResponse> call, Response<weatherResponse> response) {
                if(response.isSuccessful()){
                    weatherResponse weatherResponse = response.body();
                    temp.setText("Temperature: " + Math.round(weatherResponse.main.temp)  + " F");
                    humid.setText("Humidity: " + weatherResponse.main.humidity);

                    for(Weather weather:weatherResponse.weathers){
                        desc.setText("Description: \n" + weather.description);
                    }
                    location.setText("City: \n" + weatherResponse.name);

                }
            }

            @Override
            public void onFailure(Call<weatherResponse> call, Throwable t) {

            }
        });

    }

    public void getWeatherDataZip(String zip){
       // zip = zip + ",US";
        Log.d("beforeAPI","before");
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Log.d("beforeCALL","before");
        Call<weatherResponse> call = service.getDataZip(zip);
        Log.d("beforeENQ","before");
        call.enqueue(new Callback<weatherResponse>() {
            @Override
            public void onResponse(Call<weatherResponse> call, Response<weatherResponse> response) {
                if(response.isSuccessful()){
                    weatherResponse weatherResponse = response.body();
                    temp.setText("Temperature: " + Math.round(weatherResponse.main.temp)  + " F");
                    humid.setText("Humidity: " + weatherResponse.main.humidity);
                    for(Weather weather:weatherResponse.weathers){
                        //cloud.setText(weather.main);
                        desc.setText("Description: \n" + weather.description);
                    }
                    location.setText("City: \n" + weatherResponse.name);

                }
            }

            @Override
            public void onFailure(Call<weatherResponse> call, Throwable t) {

            }
        });

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }









    public void closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

