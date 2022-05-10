package com.example.yourcircadian.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "your_circadian.db";
    public static final String TABLE_REGISTROS = "t_registros";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REGISTROS+ "("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "fecha TEXT NOT NULL,"+
                "hora TEXT NOT NULL,"+
                "accion TEXT NOT NULL)"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_REGISTROS + " (fecha, hora, accion) " +
                        "VALUES" +
                        //Repetidos tests
                        "('2022-01-04','07:40:00','Desconexion'),"+
                        "('2022-01-04','07:40:00','Desconexion'),"+
                        //fueraDeRango tests
                        "('2022-01-15','12:40:00','Conexion'),"+
                        "('2022-01-15','20:40:00','Desconexion'),"+
                        //parIncompleto tests
                        "('2022-01-15','23:40:00','Desconexion'),"+
                        "('2022-01-16','09:40:00','Desconexion'),"+
                        //masDe14HorasDurmiendo tests
                        "('2022-01-16','23:30:00','Conexion'),"+
                        "('2022-01-17','22:10:00','Desconexion'),"+
                        //horasTotalesSuenio tests
                        "('2022-02-09','23:30:00','Conexion'),"+
                        "('2022-02-10','00:30:00','Desconexion'),"+
                        "('2022-02-10','01:30:00','Conexion'),"+
                        "('2022-02-10','09:30:00','Desconexion'),"+
                        //Grafico tests
                        "('2022-05-01','23:30:00','Conexion'),"+
                        "('2022-05-02','09:30:00','Desconexion'),"+
                        "('2022-05-02','23:30:00','Conexion'),"+
                        "('2022-05-03','09:30:00','Desconexion'),"+
                        "('2022-05-03','23:30:00','Conexion'),"+
                        "('2022-05-04','08:30:00','Desconexion'),"+
                        "('2022-05-04','23:25:00','Conexion'),"+
                        "('2022-05-05','07:05:00','Desconexion'),"+
                        "('2022-05-05','23:55:00','Conexion'),"+
                        "('2022-05-06','06:48:00','Desconexion'),"+
                        "('2022-05-06','23:00:00','Conexion'),"+
                        "('2022-05-07','06:00:00','Desconexion'),"+
                        "('2022-05-07','23:09:00','Conexion'),"+
                        "('2022-05-08','06:30:00','Desconexion'),"+
                        "('2022-05-08','23:00:00','Conexion'),"+
                        "('2022-05-09','08:38:00','Desconexion'),"+
                        "('2022-05-09','23:40:00','Conexion'),"+
                        "('2022-05-10','07:10:00','Desconexion'),"+
                        "('2022-05-11','00:10:00','Conexion'),"+
                        "('2022-05-11','07:10:00','Desconexion'),"+
                        "('2022-05-11','23:10:00','Conexion'),"+
                        "('2022-05-12','08:40:00','Desconexion'),"+
                        "('2022-05-12','23:03:00','Conexion'),"+
                        "('2022-05-13','06:03:00','Desconexion'),"+
                        "('2022-05-13','23:03:00','Conexion'),"+
                        "('2022-05-14','08:03:00','Desconexion'),"+
                        "('2022-05-14','23:33:00','Conexion'),"+
                        "('2022-05-15','08:04:00','Desconexion'),"+
                        "('2022-05-15','23:31:00','Conexion'),"+
                        "('2022-05-16','07:24:00','Desconexion'),"+
                        "('2022-05-16','23:41:00','Conexion'),"+
                        "('2022-05-17','07:24:00','Desconexion'),"+
                        "('2022-05-17','23:00:00','Conexion'),"+
                        "('2022-05-18','08:30:00','Desconexion'),"+
                        "('2022-05-18','23:10:00','Conexion'),"+
                        "('2022-05-19','08:30:00','Desconexion'),"+
                        "('2022-05-19','23:50:00','Conexion'),"+
                        "('2022-05-20','08:37:00','Desconexion'),"+
                        "('2022-05-20','23:00:00','Conexion'),"+
                        "('2022-05-21','08:20:00','Desconexion')"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_REGISTROS);
        onCreate(sqLiteDatabase);
    }
}