package com.example.yourcircadian;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneChargerConnectedListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        TextView textView1;
        textView1 = (TextView) ((AppCompatActivity)context).findViewById(R.id.textView1);
        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            textView1.setText("Conectado");
            //Toast.makeText(context,"CONECTADO", Toast.LENGTH_LONG).show();
            //context.startService(
            //        new Intent(MyService.ACTION_POWER_CONNECTED));
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            textView1.setText("Desconectado");
            //Toast.makeText(context,"DESCONECTADO", Toast.LENGTH_LONG).show();
            //context.startService(
            //        new Intent(MyService.ACTION_POWER_DISCONNECTED));
        }
    }
}
