package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileOutputStream;
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

        createAndSaveData();
    }
    public void createAndSaveData(){
        String FILENAME = "registros.csv";
        String registro = "2022-02-07" + "," + "23:30:00" + "," + "Conexion" + "\n";
        try {
            FileOutputStream out = openFileOutput( FILENAME, Context.MODE_APPEND);
            out.write(registro.getBytes());
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}