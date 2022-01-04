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

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REGISTROS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha TEXT NOT NULL," +
                "hora TEXT NOT NULL," +
                "accion TEXT NOT NULL)"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_REGISTROS + "(fecha, hora, accion) " + "VALUES('2022-01-04','19:32:34', 'Desconexion'),('2022-01-04','20:24:46', 'Conexion');"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_REGISTROS);
        onCreate(sqLiteDatabase);
    }
}