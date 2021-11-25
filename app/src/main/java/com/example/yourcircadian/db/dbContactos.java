package com.example.yourcircadian.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbContactos extends DbHelper{

    Context context;

    public dbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarContacto(String nombre, String telefono, String correo_electronico) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        /*ContentValues is a class used to insert new rows into tables. Each Content Values
        object represents a single table row as a map of column names to values.*/

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("telefono", telefono);
        values.put("correo_electronico", correo_electronico);

        long id = db.insert(TABLE_CONTACTOS, null, values);

        return id;
    }
}
