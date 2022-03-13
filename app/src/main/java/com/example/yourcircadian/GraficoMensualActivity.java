package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraficoMensualActivity extends AppCompatActivity {

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
                new DataPoint(29,7)
        };

        return dp;
    }


}