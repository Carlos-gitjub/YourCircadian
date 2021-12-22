package com.example.yourcircadian;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yourcircadian.db.DbRegistros;

public class PhoneChargerConnectedListener extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        TextView textView1;
        textView1 = (TextView) ((AppCompatActivity)context).findViewById(R.id.textView1);

        DbRegistros dbRegistros = new DbRegistros(context); //por si aca: .getApplicationContext()

        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            textView1.setText("Conectado");
            long id = dbRegistros.insertarRegistro("algo");
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            textView1.setText("Desconectado");
            long id = dbRegistros.insertarRegistro("algo");
        }
    }
}
