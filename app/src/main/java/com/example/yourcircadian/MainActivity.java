package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readWeatherData();
    }
    private List<WeatherSample> weatherSamples = new ArrayList<>();
    public void readWeatherData() {
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            //Step over headers
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                // Split by ','
                String[] tokens = line.split(",");

                // Read the data
                WeatherSample sample = new WeatherSample();
                sample.setMonth(tokens[0]);
                sample.setRainfall(Double.parseDouble(tokens[1]));
                sample.setSunHours(Integer.parseInt(tokens[2]));
                weatherSamples.add(sample);

                Log.d("MyActivity", "Just created: " + sample);
            }
        } catch (IOException e){
            Log.wtf("MyActivity", "Error al leer archivo data en linea" + line, e);
            e.printStackTrace();
        }
    }

}