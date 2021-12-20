package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yourcircadian.db.DbHelper;

public class MainActivity extends AppCompatActivity {
    private PhoneChargerConnectedListener myPhoneChargerConnectedListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  //para escribir en nuestra DB
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        myPhoneChargerConnectedListener = new PhoneChargerConnectedListener();
        registerReceiver(myPhoneChargerConnectedListener, intentFilter);
    }
    //onPause() lleva a cabo la acción cuando la app deja de estar en pantalla y pasa a segundo plano
    //si quitas onPause(), la app sigue realizando lo que pone en onResume()m aunque esta esté
    //en segundo plano. Siempre y cuando la app esté inicializada.
/*
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myPhoneChargerConnectedListener);
    }
*/

}