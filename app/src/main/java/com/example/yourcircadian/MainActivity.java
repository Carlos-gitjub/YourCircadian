package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yourcircadian.db.DbHelper;
import com.example.yourcircadian.db.DbRegistros;
import com.example.yourcircadian.entidades.Registros;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private PhoneChargerConnectedListener myPhoneChargerConnectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  //para escribir en nuestra DB
        DbRegistros dbRegistros = new DbRegistros(this);



        TextView textView1;
        TextView textView2;
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        //Date currentTime = Calendar.getInstance().getTime();
        //textView1.setText(String.valueOf(currentTime));
        textView1.setText("Te levantastes a las " + dbRegistros.hora_a_la_que_se_levanta());
        textView2.setText("Te acostastes a las " +  dbRegistros.hora_a_la_que_se_acuesta());
    }

    //Menú superior
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.vistaCalendario:
                DbRegistros dbRegistros = new DbRegistros(MainActivity.this);
                //dbRegistros.rangoNocturno();
                //dbRegistros.duplicadosMismaFechaHora();
                //dbRegistros.paresIncompletos();
                //dbRegistros.masDe14HorasDurmiendo();

                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
/*
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // yourMethod();
                        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(intent);
                    }
                }, 4000);   //5 seconds
*/
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Acciones cuando la app está en segundo plano
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