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

        DbRegistros dbRegistros = new DbRegistros(context); //por si aca: .getApplicationContext()

        Date currentTime = Calendar.getInstance().getTime();
        String registro = String.valueOf(currentTime);

        String fecha = parserFecha(registro);
        String hora = parserHora(registro);

        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            dbRegistros.insertarRegistro(fecha, hora, "Conexion");   //"Mon Dec 27 16:09:45 GMT+00:00 2021"
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            dbRegistros.insertarRegistro(fecha, hora, "Desconexion");
        }
    }

    public String parserHora(String registro){
       String hora = registro.substring(11,19);
       return hora;
    }

    public String parserFecha(String registro){
        String fecha;
        String[] splitted_parts = registro.split(" ");
        String año = splitted_parts[5];
        String mes = splitted_parts[1];
        String dia = splitted_parts[2];
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
        }
        fecha = año + "-" + mes + "-" + dia;
        return fecha;
    }
}
