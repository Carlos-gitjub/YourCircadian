package com.example.yourcircadian.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.yourcircadian.entidades.Registros;

import java.util.ArrayList;

public class DbRegistros extends DbHelper{
    Context context;

    public DbRegistros(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRegistro(String fecha, String hora, String accion) {

        long id = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

        /*ContentValues is a class used to insert new rows into tables. Each Content Values
        object represents a single table row as a map of column names to values.*/

            ContentValues values = new ContentValues();
            values.put("fecha", fecha);
            values.put("hora", hora);
            values.put("accion", accion);

            id = db.insert(TABLE_REGISTROS, null, values);

            return id;
        }catch(Exception ex){
            ex.toString();
        }

        return id;
    }
// Función que:
// le resta un día menos a las fechas cuyas horas se encuentran entre las 00:00:00 y
// 12:00:00 para que identifique cada noche de sueño como perteneciente
// a un mismo dia y no a una mezcla de dos.

    public ArrayList<Registros> mostrarRegistroAPartirDeFecha(String date) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Registros> listaRegistros = new ArrayList<>();
        Registros registro = null;
        Cursor cursorRegistros = null;
        String query = "SELECT fecha, hora, accion FROM " + TABLE_REGISTROS + " WHERE fecha = " + "'"+date+"'";
        cursorRegistros = db.rawQuery(query, null);

        if(cursorRegistros.moveToFirst()){
            do{
                registro = new Registros();
                registro.setFecha(cursorRegistros.getString(0));
                registro.setHora(cursorRegistros.getString(1));
                registro.setAccion(cursorRegistros.getString(2));
                listaRegistros.add(registro);
            }while (cursorRegistros.moveToNext());
        }

        cursorRegistros.close();
        return listaRegistros;

    }
/*
    public void fecha_noche(){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = "UPDATE t_registros " +
                "SET fecha = date(fecha,'-1 days') " +
                "WHERE hora > '00:00:00' AND hora < '12:00:00'";

        Cursor cursor = db.rawQuery(updateQuery, null);
    }*/
    public void rangoNocturno(){

    }
    public void duplicadosMismaFechaHora(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorDuplicados = null;
        String query =
                "DELETE FROM " + TABLE_REGISTROS + " WHERE id NOT IN" +
                "(" +
                "SELECT MIN(id) FROM " + TABLE_REGISTROS + " GROUP BY fecha, hora, accion" +
                ")";

        cursorDuplicados = db.rawQuery(query, null);
        cursorDuplicados.close();
    }

    public void parConex_DesconexImcompleto(){

    }
    public void tiempoEntreConexionDesconexion(){

    }

}

