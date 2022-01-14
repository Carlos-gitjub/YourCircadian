package com.example.yourcircadian.db;

import com.example.yourcircadian.entidades.Registros;

import java.util.ArrayList;

public interface FunctionsData {
    long insertarRegistro(String fecha, String hora, String accion);
    ArrayList<Registros> mostrarRegistroAPartirDeFecha(String date);
//    void transaccionParaMantenerIntegridadBaseDatos();
    void rangoNocturno();
    void duplicadosMismaFechaHora();
}
