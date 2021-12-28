package com.example.yourcircadian.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

public class DbRegistros extends DbHelper{
    Context context;

    public DbRegistros(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRegistro(String noche) {

        long id = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

        /*ContentValues is a class used to insert new rows into tables. Each Content Values
        object represents a single table row as a map of column names to values.*/

            ContentValues values = new ContentValues();
            values.put("noche", noche);

            id = db.insert(TABLE_REGISTROS, null, values);

            return id;
        }catch(Exception ex){
            ex.toString();
        }

        return id;
    }
}
