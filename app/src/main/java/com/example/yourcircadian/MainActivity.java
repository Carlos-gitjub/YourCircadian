package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

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

        //GRÁFICO
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(getDataPoint());
        graph.addSeries(series);
        //El StaticLabelFormatter no pone los valores, pero es necesario dejarlo para que aparezcan
        //todos los dias de la semana en el eje x y no de dos en dos.
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "L", "M", "X", "J", "V", "S", "D", ""});
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {

                    if (value == 0) {
                        return ""; //+ super.formatLabel(value, isValueX);
                    }
                    if (value == 1) {
                        return "L";
                    }
                    if (value == 2) {
                        return "M";
                    }
                    if (value == 3) {
                        return "X";
                    }
                    if (value == 4) {
                        return "J";
                    }
                    if (value == 5) {
                        return "V";
                    }
                    if (value == 6) {
                        return "S";
                    }
                    if (value == 7) {
                        return "D";
                    }
                    if (value == 8) {
                        return "";
                    }

                } else {
                    if (value==0){
                        return "";
                    }
                }

                return super.formatLabel(value, isValueX);
            }
        });
        graph.setTitle("horas sueño:");
        //rango que cubre eje X
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(8);
        //rango que cubre eje Y
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(12);
        series.setSpacing(50);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

        //TEXTVIEWS
        TextView textView1;
        TextView textView2;
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        //Date currentTime = Calendar.getInstance().getTime();
        //textView1.setText(String.valueOf(currentTime));
        textView1.setText("Te levantastes a las " + dbRegistros.hora_a_la_que_se_levanta());
        textView2.setText("Te acostastes a las " +  dbRegistros.hora_a_la_que_se_acuesta());
    }

    //MENU SUPERIOR
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

    //DATAPOINTS
    private DataPoint[] getDataPoint() {
        DataPoint[] dp = new DataPoint[]
                {
                        new DataPoint(0,0),
                        new DataPoint(1,8.6),
                        new DataPoint(2,9),
                        new DataPoint(3,7),
                        new DataPoint(4,8),
                        new DataPoint(5,7),
                        new DataPoint(6,4),
                        new DataPoint(7,6),
                        //new DataPoint(8,-1)
                };
        return dp;
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