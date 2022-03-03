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
                            //Repetidos
                            "('2022-01-04','07:40:00','Desconexion')," +
                            "('2022-01-04','07:40:00','Desconexion')," +
                            //fueraDeRango
                            "('2022-01-15','12:40:00','Conexion')," +
                            "('2022-01-15','20:40:00','Desconexion')," +
                            //parIncompleto
                            "('2022-01-15','23:40:00','Desconexion')," +
                            "('2022-01-16','09:40:00','Desconexion')," +
                            //masDe14HorasDurmiendo
                            "('2022-01-16','23:30:00','Conexion')," +
                            "('2022-01-17','22:10:00','Desconexion')," +
                            //horasTotalesSuenio
                            "('2022-02-09','23:30:00','Conexion')," +
                            "('2022-02-10','00:30:00','Desconexion')," +
                            "('2022-02-10','01:30:00','Conexion')," +
                            "('2022-02-10','09:30:00','Desconexion')"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_REGISTROS);
        onCreate(sqLiteDatabase);
    }
}