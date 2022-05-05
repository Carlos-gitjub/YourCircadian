package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.yourcircadian.db.DbRegistros;

public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        DbRegistros dbRegistros = new DbRegistros(this);

        double[] horas_31_fechas = dbRegistros.dias_mes_DB();

        int contador = 0;
        double sumatorio = 0;
        double media = 0;
        for (int i = 0; i < 31; i++) {
            double horas = horas_31_fechas[i];
            if ( horas != 0.0) {
                contador++;
                sumatorio += horas_31_fechas[i];
            }
        }
        media = sumatorio / contador;
        String media_string = String.valueOf(media);
        Toast.makeText(MediaActivity.this,media_string,Toast.LENGTH_SHORT).show();

    }
}