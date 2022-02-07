package com.example.yourcircadian;

public class Registros {
    private String fecha;
    private String hora;
    private String accion;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

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

    @Override
    public String toString() {
        return "Registros{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", accion='" + accion + '\'' +
                '}';
    }
}
