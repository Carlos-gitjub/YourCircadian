package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(getDataPoint());
        graph.addSeries(series);


        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {

                if(isValueX) {
/*
                    if (value == 0) {
                        return "";//+ super.formatLabel(value, isValueX);
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
                    */

                }else {
                    if (value == -1) {
                        return "";
                    }
                }
                return super.formatLabel(value, isValueX);
            }
        });


        /*
        //rango que cubre eje X
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(3);

         */
        //rango que cubre eje Y
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(12);


        series.setSpacing(25);

        //valores encima de columnas
        //BarGraphSeries<DataPoint> series2;
        //series.
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "L", "M", "X", "J", "V", "S", "D", ""});
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.grafico_2:
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private DataPoint[] getDataPoint() {
        DataPoint[] dp = new DataPoint[]
                {
                        new DataPoint(0,-1),
                        new DataPoint(1,9),
                        new DataPoint(2,2),
                        new DataPoint(3,6),
                        new DataPoint(4,8),
                        new DataPoint(5,7),
                        new DataPoint(6,9),
                        new DataPoint(7,8),
                        new DataPoint(8,-1)
                        /*
                        new DataPoint(0,-1),
                        new DataPoint(1,8),
                        new DataPoint(2,9),
                        new DataPoint(3,7),
                        new DataPoint(4,8),
                        new DataPoint(5,7),
                        new DataPoint(6,4),
                        new DataPoint(7,6),
                        new DataPoint(8,-1)

                         */
                };
        return dp;
    }
}