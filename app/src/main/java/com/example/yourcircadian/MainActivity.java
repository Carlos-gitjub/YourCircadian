package com.example.yourcircadian;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
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
                }
                return super.formatLabel(value, isValueX);
            }
        });

        series.setSpacing(25);

        //valores encima de columnas
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
    }

    private DataPoint[] getDataPoint() {
        DataPoint[] dp = new DataPoint[]
                {
                        //new DataPoint(0,0),
                        new DataPoint(1,8),
                        new DataPoint(2,9),
                        new DataPoint(3,7),
                        new DataPoint(4,8),
                        new DataPoint(5,7.5),
                        new DataPoint(6,4),
                        new DataPoint(7,8),
                };
        return dp;
    }
}