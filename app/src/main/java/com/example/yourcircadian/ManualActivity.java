package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yourcircadian.db.DbRegistros;

import java.util.Calendar;
import java.util.Date;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

    }
    public void comienza(View view) {
        DbRegistros dbRegistros = new DbRegistros(this);
        Date currentTime = Calendar.getInstance().getTime();
        String registro = String.valueOf(currentTime);

        PhoneChargerConnectedListener phoneChargerConnectedListener = new PhoneChargerConnectedListener();

        String fecha = phoneChargerConnectedListener.parserFecha(registro);
        String hora = phoneChargerConnectedListener.parserHora(registro);
        dbRegistros.insertarRegistro(fecha,hora,"Conexion");

        Toast.makeText(ManualActivity.this,"Buenas noches",Toast.LENGTH_SHORT).show();
    }
    public void finaliza(View view){
        DbRegistros dbRegistros = new DbRegistros(this);
        Date currentTime = Calendar.getInstance().getTime();
        String registro = String.valueOf(currentTime);

        PhoneChargerConnectedListener phoneChargerConnectedListener = new PhoneChargerConnectedListener();

        String fecha = phoneChargerConnectedListener.parserFecha(registro);
        String hora = phoneChargerConnectedListener.parserHora(registro);
        dbRegistros.insertarRegistro(fecha,hora,"Desconexion");

        Toast.makeText(ManualActivity.this,"Buenos días",Toast.LENGTH_SHORT).show();
    }
}