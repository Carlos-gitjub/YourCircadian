package com.example.yourcircadian.entidades;

public class Registros {
    private String fecha;
    private String hora;
    private String accion;
    //private String diaSemana;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    //public String getDiaSemana() { return diaSemana; }

    //public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
}
