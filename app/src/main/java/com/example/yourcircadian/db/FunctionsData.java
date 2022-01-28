package com.example.yourcircadian.db;

import com.example.yourcircadian.entidades.Registros;

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
}
