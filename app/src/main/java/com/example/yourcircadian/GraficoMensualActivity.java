package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.yourcircadian.db.DbRegistros;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public class GraficoMensualActivity extends AppCompatActivity {
    DbRegistros dbRegistros = new DbRegistros(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_mensual);
        TextView textView = (TextView) findViewById(R.id.textView_media_numerica_mensual);
        GraphView graphView = (GraphView) findViewById(R.id.graphMensual);

        //Añadir valores al objeto del gráfico
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(getDataPoints());
        graphView.addSeries(series);
        //Eje x
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(32);
        series.setSpacing(15);
        //Eje y
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(12);
        //Titulo del grafico
        graphView.setTitle("horas de sueño este mes:");
        graphView.setTitleTextSize(40);
        //Color de las barras
        //series.setColor();

        textView.setText("Media diaria: "+ dbRegistros.mediaNumericaMensual());
    }

    private DataPoint[] getDataPoints() {
        ArrayList<String> dia = new ArrayList<>();

        /*
        * El vector que viene a continuación es unvector de tamaño 31 que contiene las horas de
        * sueño calculadas para cada dia del mes el vector sigue el orden de el mes
        * (primer indice se refiere a primer dia y asi sucesivamente). ACLARACION: aunque no haya 31
        * días sigue siendo valido porque el valor que tendría para el dia que no exista seria 0.
        */
        double[] fechas = dbRegistros.diasMesDB();

        DataPoint[] dp = new DataPoint[]{
                new DataPoint(0,0),
                new DataPoint(1,fechas[0]),
                new DataPoint(2,fechas[1]),
                new DataPoint(3,fechas[2]),
                new DataPoint(4,fechas[3]),
                new DataPoint(5,fechas[4]),
                new DataPoint(6,fechas[5]),
                new DataPoint(7,fechas[6]),
                new DataPoint(8,fechas[7]),
                new DataPoint(9,fechas[8]),
                new DataPoint(10,fechas[9]),
                new DataPoint(11,fechas[10]),
                new DataPoint(12,fechas[11]),
                new DataPoint(13,fechas[12]),
                new DataPoint(14,fechas[13]),
                new DataPoint(15,fechas[14]),
                new DataPoint(16,fechas[15]),
                new DataPoint(17,fechas[16]),
                new DataPoint(18,fechas[17]),
                new DataPoint(19,fechas[18]),
                new DataPoint(20,fechas[19]),
                new DataPoint(21,fechas[20]),
                new DataPoint(22,fechas[21]),
                new DataPoint(23,fechas[22]),
                new DataPoint(24,fechas[23]),
                new DataPoint(25,fechas[24]),
                new DataPoint(26,fechas[25]),
                new DataPoint(27,fechas[26]),
                new DataPoint(28,fechas[27]),
                new DataPoint(29,fechas[28]),
                new DataPoint(30,fechas[29]),
                new DataPoint(31,fechas[30])

        };

        return dp;
    }


}