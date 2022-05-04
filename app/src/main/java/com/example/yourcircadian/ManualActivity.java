package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.yourcircadian.db.DbRegistros;

import java.util.Calendar;
import java.util.Date;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        DbRegistros dbRegistros = new DbRegistros(this);

        Date currentTime = Calendar.getInstance().getTime();
        String registro = String.valueOf(currentTime);

        PhoneChargerConnectedListener phoneChargerConnectedListener = new PhoneChargerConnectedListener();

        String fecha = phoneChargerConnectedListener.parserFecha(registro);
        String hora = phoneChargerConnectedListener.parserHora(registro);

    }
}