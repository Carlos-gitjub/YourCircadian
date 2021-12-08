package com.example.yourcircadian;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneChargerConnectedListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            Toast.makeText(context, "CONNECTED. Broadcast Receiver Triggered", Toast.LENGTH_LONG).show();
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            Toast.makeText(context, "DISCONNECTED. Broadcast Receiver Triggered", Toast.LENGTH_LONG).show();
        }
    }
}
