package com.example.yourcircadian;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

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
        DbRegistros dbRegistros = new DbRegistros(CalendarActivity.this);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date;
                String anio = String.valueOf(year);
                String mes = String.valueOf(month); //NOTA: los meses los nombra del 0 al 11.
                String dia = String.valueOf(dayOfMonth);

                //Pasamos mes a correcto formato
                switch (mes){
                    case "0":
                        mes = "01";
                        break;
                    case "1":
                        mes = "02";
                        break;
                    case "2":
                        mes = "03";
                        break;
                    case "3":
                        mes = "04";
                        break;
                    case "4":
                        mes = "05";
                        break;
                    case "5":
                        mes = "06";
                        break;
                    case "6":
                        mes = "07";
                        break;
                    case "7":
                        mes = "08";
                        break;
                    case "8":
                        mes = "09";
                        break;
                    case "9":
                        mes = "10";
                        break;
                    case "10":
                        mes = "11";
                        break;
                    case "11":
                        mes = "12";
                        break;
                }

                //Pasamos dia (dia) a correcto formato si está incorrecto
                switch (dia){
                    case "1":
                        dia = "01";
                        break;
                    case "2":
                        dia = "02";
                        break;
                    case "3":
                        dia = "03";
                        break;
                    case "4":
                        dia = "04";
                        break;
                    case "5":
                        dia = "05";
                        break;
                    case "6":
                        dia = "06";
                        break;
                    case "7":
                        dia = "07";
                        break;
                    case "8":
                        dia = "08";
                        break;
                    case "9":
                        dia = "09";
                        break;
                }

                date = anio + "-" + mes + "-" + dia;
                String timeSlept = dbRegistros.horasTotalesDeSuenio(date);
                Toast.makeText(CalendarActivity.this,timeSlept,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
