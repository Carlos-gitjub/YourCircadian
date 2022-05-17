package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yourcircadian.db.DbHelper;
import com.example.yourcircadian.db.DbRegistros;
import com.example.yourcircadian.entidades.Weekday;
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

        //GRÁFICO semanal
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(getDataPoint());
        graph.addSeries(series);
        /*
        * El StaticLabelsFormatter no pondrá los valores (los pone el DefaultLabelFormatter()), pero es necesario dejarlo para que aparezcan
        * todos y cada uno de los dias de la semana en el eje x y no de dos en dos.
         */
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "L", "M", "X", "J", "V", "S", "D", ""});
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        //Establece los nombres de las etiquetas de el eje x
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {

                    if (value == 0) {
                        return "";
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

        //Titulo grafico
        graph.setTitle("horas de sueño esta semana:");
        graph.setTitleTextSize(40);
        //Eje X
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(8);
        //Eje Y
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

        textView2.setText(dbRegistros.horaALaQueSeAcuesta());
        textView1.setText(dbRegistros.horaALaQueSeLevanta());
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

                Intent intent1 = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent1);
                return true;

            case R.id.mediaMensual:
                Intent intent2 = new Intent(MainActivity.this, GraficoMensualActivity.class);
                startActivity(intent2);
                return true;

            case R.id.contacto:
                Intent intent3 = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(intent3);
                return true;

            case R.id.manual:
                Intent intent4 = new Intent(MainActivity.this,ManualActivity.class);
                startActivity(intent4);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    //DATAPOINTS de esta semana
    private DataPoint[] getDataPoint() {
        DbRegistros dbRegistros = new DbRegistros(this);
        ArrayList<Weekday> listaDiasSemana = dbRegistros.diasSEMANA();
        double lunes=0,martes=0,miercoles=0,jueves=0,viernes=0,sabado=0,domingo=0;
        //Obtención de valores segun el formato de dias de la semana de SQLite (el domingo es 0)
        for(int i=0; i<listaDiasSemana.size(); i++){
            switch (listaDiasSemana.get(i).getWeekday()) {
                case "1":
                    lunes = dbRegistros.horasTotalesDeSuenioGraphView(listaDiasSemana.get(i).getFecha());
                    break;
                case "2":
                    martes = dbRegistros.horasTotalesDeSuenioGraphView(listaDiasSemana.get(i).getFecha());
                    break;
                case "3":
                    miercoles = dbRegistros.horasTotalesDeSuenioGraphView(listaDiasSemana.get(i).getFecha());
                    break;
                case "4":
                    jueves = dbRegistros.horasTotalesDeSuenioGraphView(listaDiasSemana.get(i).getFecha());
                    break;
                case "5":
                    viernes = dbRegistros.horasTotalesDeSuenioGraphView(listaDiasSemana.get(i).getFecha());
                    break;
                case "6":
                    sabado = dbRegistros.horasTotalesDeSuenioGraphView(listaDiasSemana.get(i).getFecha());
                    break;
                case "0":
                    domingo = dbRegistros.horasTotalesDeSuenioGraphView(listaDiasSemana.get(i).getFecha());
                    break;
            }
        }


        DataPoint[] dp = new DataPoint[]
                {
                        new DataPoint(0,0),
                        new DataPoint(1,lunes),
                        new DataPoint(2,martes),
                        new DataPoint(3,miercoles),
                        new DataPoint(4,jueves),
                        new DataPoint(5,viernes),
                        new DataPoint(6,sabado),
                        new DataPoint(7,domingo)
                };

        return dp;
    }

    //Esta función define cciones cuando la app está en segundo plano
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        myPhoneChargerConnectedListener = new PhoneChargerConnectedListener();
        registerReceiver(myPhoneChargerConnectedListener, intentFilter);
    }
    /*
    * onPause() lleva a cabo la acción cuando la app deja de estar en pantalla y pasa a segundo plano
    * si quitas onPause(), la app sigue realizando lo que pone en onResume()m aunque esta esté
    * en segundo plano. Siempre y cuando la app esté inicializada.

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myPhoneChargerConnectedListener);
    }
    */

}