package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private PhoneChargerConnectedListener myPhoneChargerConnectedListener;
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        myPhoneChargerConnectedListener = new PhoneChargerConnectedListener();
        registerReceiver(myPhoneChargerConnectedListener, intentFilter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myPhoneChargerConnectedListener);
    }
}