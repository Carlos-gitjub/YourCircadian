package com.example.yourcircadian.db;

import com.example.yourcircadian.entidades.Registros;
import com.example.yourcircadian.entidades.Weekday;

import java.util.ArrayList;

public interface FunctionsData {
    void insertarRegistro(String fecha, String hora, String accion);
    ArrayList<Registros> mostrarRegistroAPartirDeFecha(String date);
    void rangoNocturno();
    void duplicadosMismaFechaHora();
    //void lapsosDeTiempoPequenios();
    void paresIncompletos();
    void masDe14HorasDurmiendo();

    String hora_a_la_que_se_levanta();
    String hora_a_la_que_se_acuesta();
    String horas_totales_de_suenio(String date);     //Usado en CalendarActivity
    double horas_totales_de_suenio_GraphView(String date); //Usado en MainActivity en el grafico
    ArrayList<Weekday> diasSEMANA();
}
