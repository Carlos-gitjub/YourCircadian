package com.example.yourcircadian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yourcircadian.db.DbRegistros;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        //PRUEBA:
        DbRegistros dbRegistros = new DbRegistros(CalendarActivity.this);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date;
                String YYYY = String.valueOf(year);
                String MM = String.valueOf(month); //Formato: los meses los pasa del 0 al 11.
                //Pasamos mes a correcto formato
                switch (MM){
                    case "0":
                        MM = "01";
                        break;
                    case "1":
                        MM = "02";
                        break;
                    case "2":
                        MM = "03";
                        break;
                    case "3":
                        MM = "04";
                        break;
                    case "4":
                        MM = "05";
                        break;
                    case "5":
                        MM = "06";
                        break;
                    case "6":
                        MM = "07";
                        break;
                    case "7":
                        MM = "08";
                        break;
                    case "8":
                        MM = "09";
                        break;
                    case "9":
                        MM = "10";
                        break;
                    case "10":
                        MM = "11";
                        break;
                    case "11":
                        MM = "12";
                        break;
                }
                String DD = String.valueOf(dayOfMonth);
                //Pasamos DD (dia) a correcto formato si está incorrecto
                switch (DD){
                    case "1":
                        DD = "01";
                        break;
                    case "2":
                        DD = "02";
                        break;
                    case "3":
                        DD = "03";
                        break;
                    case "4":
                        DD = "04";
                        break;
                    case "5":
                        DD = "05";
                        break;
                    case "6":
                        DD = "06";
                        break;
                    case "7":
                        DD = "07";
                        break;
                    case "8":
                        DD = "08";
                        break;
                    case "9":
                        DD = "09";
                        break;
                }

                date = YYYY + "-" + MM + "-" + DD;
                String timeSleep = dbRegistros.horas_totales_de_suenio(date);
                Toast.makeText(CalendarActivity.this,timeSleep,Toast.LENGTH_SHORT).show();
/*
                Intent intent = new Intent(CalendarActivity.this, ShowDateActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
 */
            }
        });
    }
}
