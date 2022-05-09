package com.example.yourcircadian.db;

import com.example.yourcircadian.entidades.Registros;
import com.example.yourcircadian.entidades.Weekday;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public interface FunctionsData {
    void insertarRegistro(String fecha, String hora, String accion); //Usado en PhoneChargerConnectedListener y en ManualActivity
    String hora_a_la_que_se_levanta(); //Usada en MainActivity
    String hora_a_la_que_se_acuesta(); //Usada en MainActivity
    String horas_totales_de_suenio(String date);     //Usado en CalendarActivity
    double horas_totales_de_suenio_GraphView(String date); //Usado en MainActivity en el grafico
    ArrayList<Weekday> diasSEMANA(); // Usada en MainActivity
    String media_numerica_mensual(); // Usada en GraficoMensualActivity
    double[] dias_mes_DB();  // Usada en GraficoMensualActivity
}
