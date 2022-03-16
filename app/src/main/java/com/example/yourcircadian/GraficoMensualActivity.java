package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.yourcircadian.db.DbRegistros;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraficoMensualActivity extends AppCompatActivity {
    DbRegistros dbRegistros = new DbRegistros(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_mensual);

        GraphView graphView = (GraphView) findViewById(R.id.graphMensual);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());
        graphView.addSeries(series);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMaxX(32);
    }

    private DataPoint[] getDataPoint() {
        ArrayList<String> dia = new ArrayList<>();
        //double[] fechas = dbRegistros.dias_mes_DB();

        DataPoint[] dp = new DataPoint[]{
                new DataPoint(0,1),
                new DataPoint(1,1),
                new DataPoint(2,7),
                new DataPoint(3,1),
                new DataPoint(4,1),
                new DataPoint(5,5),
                new DataPoint(6,1),
                new DataPoint(7,1),
                new DataPoint(8,1),
                new DataPoint(9,1),
                new DataPoint(10,2),
                new DataPoint(11,1),
                new DataPoint(12,4),
                new DataPoint(13,1),
                new DataPoint(14,1),
                new DataPoint(15,3),
                new DataPoint(16,1),
                new DataPoint(17,7),
                new DataPoint(18,1),
                new DataPoint(19,1),
                new DataPoint(20,1),
                new DataPoint(21,1),
                new DataPoint(22,1),
                new DataPoint(23,1),
                new DataPoint(24,5),
                new DataPoint(25,1),
                new DataPoint(26,1),
                new DataPoint(27,1),
                new DataPoint(28,1),
                new DataPoint(29,7),
                new DataPoint(30,1)

                 /*
                new DataPoint(0,fechas[0]),
                new DataPoint(1,fechas[1]),
                new DataPoint(2,fechas[2]),
                new DataPoint(3,fechas[3]),
                new DataPoint(4,fechas[4]),
                new DataPoint(5,fechas[5]),
                new DataPoint(6,fechas[6]),
                new DataPoint(7,fechas[7]),
                new DataPoint(8,fechas[8]),
                new DataPoint(9,fechas[9]),
                new DataPoint(10,fechas[10]),
                new DataPoint(11,fechas[11]),
                new DataPoint(12,fechas[12]),
                new DataPoint(13,fechas[13]),
                new DataPoint(14,fechas[14]),
                new DataPoint(15,fechas[15]),
                new DataPoint(16,fechas[16]),
                new DataPoint(17,fechas[17]),
                new DataPoint(18,fechas[18]),
                new DataPoint(19,fechas[19]),
                new DataPoint(20,fechas[20]),
                new DataPoint(21,fechas[21]),
                new DataPoint(22,fechas[22]),
                new DataPoint(23,fechas[23]),
                new DataPoint(24,fechas[24]),
                new DataPoint(25,fechas[25]),
                new DataPoint(26,fechas[26]),
                new DataPoint(27,fechas[27]),
                new DataPoint(28,fechas[28]),
                new DataPoint(29,fechas[29])
                //new DataPoint(30,fechas[30])

                 */

        };

        return dp;
    }


}