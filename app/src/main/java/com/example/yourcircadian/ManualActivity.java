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
    /*
    * NOTA: La llamada a las siguientes funciones está programada en el layout "activity_manual.xml" no
    * en esta clase
     */
    public void comienzaSuenio(View view) {
        DbRegistros dbRegistros = new DbRegistros(this);
        Date currentTime = Calendar.getInstance().getTime();
        String registro = String.valueOf(currentTime);

        PhoneChargerConnectedListener phoneChargerConnectedListener = new PhoneChargerConnectedListener();

        String fecha = phoneChargerConnectedListener.formatearFecha(registro);
        String hora = phoneChargerConnectedListener.formatearHora(registro);
        dbRegistros.insertarRegistro(fecha,hora,"Conexion");

        Toast.makeText(ManualActivity.this,"Buenas noches",Toast.LENGTH_SHORT).show();
    }

    public void finalizaSuenio(View view){
        DbRegistros dbRegistros = new DbRegistros(this);
        Date currentTime = Calendar.getInstance().getTime();
        String registro = String.valueOf(currentTime);

        PhoneChargerConnectedListener phoneChargerConnectedListener = new PhoneChargerConnectedListener();

        String fecha = phoneChargerConnectedListener.formatearFecha(registro);
        String hora = phoneChargerConnectedListener.formatearHora(registro);
        dbRegistros.insertarRegistro(fecha,hora,"Desconexion");

        Toast.makeText(ManualActivity.this,"Buenos días",Toast.LENGTH_SHORT).show();
    }
}