package com.example.yourcircadian;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;


import androidx.appcompat.app.AppCompatActivity;

import com.example.yourcircadian.db.DbRegistros;

public class PhoneChargerConnectedListener extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        TextView textView1;
        textView1 = (TextView) ((AppCompatActivity)context).findViewById(R.id.textView1);

        DbRegistros dbRegistros = new DbRegistros(context); //por si aca: .getApplicationContext()

        Date currentTime = Calendar.getInstance().getTime();
        String registro = String.valueOf(currentTime);
        separarStringFechaHora(registro); //lo separa en dos strings(fecha y hora)

        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            //textView1.setText("Conectado");
            textView1.setText(String.valueOf(currentTime));          //Mon Dec 27 16:09:45 GMT+00:00 2021
            long id = dbRegistros.insertarRegistro("Conectado " + String.valueOf(currentTime));
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            //textView1.setText("Desconectado");
            textView1.setText(String.valueOf(currentTime));
            long id = dbRegistros.insertarRegistro("Desconectado " + String.valueOf(currentTime));
        }
    }

    public void separarStringFechaHora(String registro){
       String hora = registro.substring(11,19);
       Log.v("PRUEBA", hora);
    }
}
