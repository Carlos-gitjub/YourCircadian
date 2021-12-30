package com.example.yourcircadian;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
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

        String hora = parserHora(registro); //lo separa en dos strings(fecha y hora)
        String fecha = parserFecha(registro);
        Log.v("PRUEBA", fecha);
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

    public String parserHora(String registro){
       String hora = registro.substring(11,13) + registro.substring(14,16) + registro.substring(17,19);
       return hora;
    }

    public String parserFecha(String registro){
        String mes = registro.substring(4,7);
        switch (mes){
            case "Jan":
                mes = "01";
                break;
            case "Feb":
                mes = "02";
                break;
            case "Mar":
                mes = "03";
                break;
            case "Apr":
                mes = "04";
                break;
            case "May":
                mes = "05";
                break;
            case "Jun":
                mes = "06";
                break;
            case "Jul":
                mes = "07";
                break;
            case "Aug":
                mes = "08";
                break;
            case "Sep":
                mes = "09";
                break;
            case "Oct":
                mes = "10";
                break;
            case "Nov":
                mes = "11";
                break;
            case "Dec":
                mes = "12";
                break;
            default:
        }
        String fecha = registro.substring(30,34) + mes + registro.substring(8,10);
        return fecha;
    }
}
