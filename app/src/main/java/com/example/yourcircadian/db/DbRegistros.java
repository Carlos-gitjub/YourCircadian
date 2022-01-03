package com.example.yourcircadian.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

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
    public void fecha_noche(){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = "UPDATE t_registros " +
                "SET fecha = date(fecha,'-1 days') " +
                "WHERE hora > '00:00:00' AND hora < '12:00:00'";

        Cursor cursor = db.rawQuery(updateQuery, null);
    }
    public void rangoNocturno(){

    }
    public void duplicadosMismaFechaHora(){

/*
        SELECT
                id,
                noche
        FROM(
                SELECT *,
                row_number() OVER (PARTITION BY noche ORDER BY id) as RowNbr
                FROM t_registros
        ) source
        WHERE RowNbr = 1 ORDER BY id

*/
    }
    public void parConex_DesconexImcompleto(){

    }
    public void tiempoEntreConexionDesconexion(){

    }

}

