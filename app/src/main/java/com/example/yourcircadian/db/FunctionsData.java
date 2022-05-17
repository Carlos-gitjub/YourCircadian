package com.example.yourcircadian.db;

import com.example.yourcircadian.entidades.Registros;
import com.example.yourcircadian.entidades.Weekday;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public interface FunctionsData {
    void insertarRegistro(String fecha, String hora, String accion); //Usado en PhoneChargerConnectedListener y en ManualActivity
    String horaALaQueSeLevanta(); //Usada en MainActivity
    String horaALaQueSeAcuesta(); //Usada en MainActivity
    String horasTotalesDeSuenio(String date);     //Usado en CalendarActivity
    double horasTotalesDeSuenioGraphView(String date); //Usado en MainActivity en el grafico
    ArrayList<Weekday> diasSEMANA(); // Usada en MainActivity
    String mediaNumericaMensual(); // Usada en GraficoMensualActivity
    double[] diasMesDB();  // Usada en GraficoMensualActivity
}
