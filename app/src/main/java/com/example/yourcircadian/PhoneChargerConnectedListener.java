package com.example.yourcircadian;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PhoneChargerConnectedListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            Toast.makeText(context,"CONECTADO", Toast.LENGTH_LONG).show();
            //context.startService(
            //        new Intent(MyService.ACTION_POWER_CONNECTED));
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            Toast.makeText(context,"DESCONECTADO", Toast.LENGTH_LONG).show();
            //context.startService(
            //        new Intent(MyService.ACTION_POWER_DISCONNECTED));
        }
    }
}
