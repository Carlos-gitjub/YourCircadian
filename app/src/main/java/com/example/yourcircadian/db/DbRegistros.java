package com.example.yourcircadian.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.yourcircadian.R;
import com.example.yourcircadian.entidades.Registros;
import com.example.yourcircadian.entidades.Weekday;

import java.util.ArrayList;

public class DbRegistros extends DbHelper implements FunctionsData{
    Context context;

    public DbRegistros(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void insertarRegistro(String fecha, String hora, String accion) {
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

        /*ContentValues is a class used to insert new rows into tables. Each Content Values
        object represents a single table row as a map of column names to values.*/

            ContentValues values = new ContentValues();
            values.put("fecha", fecha);
            values.put("hora", hora);
            values.put("accion", accion);

            db.insert(TABLE_REGISTROS, null, values);


        }catch(Exception ex){
            ex.toString();
        }
    }

// Función a continución, que:
// le resta un día menos a las fechas cuyas horas se encuentran entre las 00:00:00 y
// 12:00:00 para que identifique cada noche de sueño como perteneciente
// a un mismo dia y no a una mezcla de dos.

    public ArrayList<Registros> mostrarRegistroAPartirDeFecha(String date) {
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


    public void rangoNocturno(){
        //Esta función elimina los valores que no se encuentran dentro del horario normal de sueño,
        //en este caso se ha establecido desde las 21:00 hasta las 12:00

        String horaMin = "21:00:00";
        String horaMax = "12:00:00";
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = this.getWritableDatabase();
        String query =
                "DELETE FROM " + TABLE_REGISTROS +
                        " WHERE hora < '21:00:00' AND hora > '12:00:00'";
        db.execSQL(query);
    }
    public void duplicadosMismaFechaHora(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query =
                "DELETE FROM " + TABLE_REGISTROS + " WHERE id NOT IN" +
                "(" +
                "SELECT MIN(id) FROM " + TABLE_REGISTROS + " GROUP BY fecha, hora, accion" +
                ")";
        db.execSQL(query);
    }


    public void paresIncompletos() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "";
        db.execSQL(query);
    }

    public void masDe14HorasDurmiendo() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "";
        db.execSQL(query);
    }

    @Override
    public String hora_a_la_que_se_levanta() {
        SQLiteDatabase db = this.getWritableDatabase();
        String hora_con_segundos = null;
        String hora="";

        Cursor cursorRegistros = null;
        String query = "SELECT hora FROM t_registros "+
                "WHERE id = (SELECT MAX(id) FROM t_registros WHERE (hora > '21:00:00' OR hora < '12:00:00') AND accion = 'Desconexion') "+
                "AND (fecha = (SELECT DATE('now')) OR ( fecha = (SELECT DATE('now','-1 days')) AND (hora >= '00:00:00' AND hora <= '01:30:00')))";
        cursorRegistros = db.rawQuery(query, null);
        if(cursorRegistros.moveToFirst()){
           do{
                hora_con_segundos = cursorRegistros.getString(0);
           }while (cursorRegistros.moveToNext());
        }else{
            return hora;
        }
        hora = "Hoy te levantastes a las "+ hora_con_segundos.substring(0,5);

        cursorRegistros.close();
        return hora;
    }
    public String hora_a_la_que_se_acuesta() {
        SQLiteDatabase db = this.getWritableDatabase();
        String hora_con_segundos = null;
        String hora="";

        Cursor cursorRegistros = null;
        //String q = "SELECT hora FROM t_registros WHERE id = (SELECT max(id) FROM t_registros WHERE (hora > '21:00:00' OR hora < '12:00:00') AND accion = 'Conexion')";
        String query = "SELECT hora FROM t_registros " +
                "WHERE id = (SELECT max(id) FROM t_registros WHERE (hora > '21:00:00' OR hora < '12:00:00') AND accion = 'Conexion') " +
                "AND (fecha = (SELECT DATE('now')) OR fecha = (SELECT DATE('now','-1 days')))";
        cursorRegistros = db.rawQuery(query, null);
        if(cursorRegistros.moveToFirst()){
            do{
                hora_con_segundos = cursorRegistros.getString(0);
            }while (cursorRegistros.moveToNext());
        }else{
            cursorRegistros.close();
            return hora;
        }

        hora = "Anoche te acostastes a las "+ hora_con_segundos.substring(0,5);

        cursorRegistros.close();
        return hora;
    }


    public void depurarYActualizarTabla(){
        this.rangoNocturno();
        this.duplicadosMismaFechaHora();
    }

    public void parConex_DesconexImcompleto(){

    }
    public void tiempoEntreConexionDesconexion(){

    }

    public String horas_totales_de_suenio(String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Registros> listaRegistros = new ArrayList<>();
        Registros registro = null;
        Cursor cursorRegistros = null;

        String query1="SELECT fecha,hora,accion FROM t_registros WHERE "+
                "fecha=(SELECT DATE(fecha,'+1 day') FROM t_registros WHERE fecha='"+ date+ "') "+
                "OR fecha='"+ date+ "'";
        cursorRegistros=db.rawQuery(query1, null);



        if(cursorRegistros.moveToFirst()){
            do{
                registro = new Registros();

                registro.setFecha(cursorRegistros.getString(0));
                registro.setHora(cursorRegistros.getString(1));
                registro.setAccion(cursorRegistros.getString(2));

                listaRegistros.add(registro);
            }while (cursorRegistros.moveToNext());
        }

        int h1, m1, h2, m2, enMin1, enMin2;
        String accion1="";
        String accion2="";
        int suenioEnMin, hSuenio, mSuenio;
        String tDefinitivo="";
        int sumHras=0;
        int sumMns=0;
        for(int i=1;i<listaRegistros.size();i++) {
            h1 = Integer.parseInt(listaRegistros.get(i - 1).getHora().substring(0, 2));
            m1 = Integer.parseInt(listaRegistros.get(i - 1).getHora().substring(3, 5));
            h2 = Integer.parseInt(listaRegistros.get(i).getHora().substring(0, 2));
            m2 = Integer.parseInt(listaRegistros.get(i).getHora().substring(3, 5));
            enMin1 = h1 * 60 + m1;
            enMin2 = h2 * 60 + m2;
            accion1 = listaRegistros.get(i - 1).getAccion();
            accion2 = listaRegistros.get(i).getAccion();

            if (h2 >= 00 && h2 <= 12 && accion1.equals("Conexion") && accion2.equals("Desconexion")) {
                if (h1 >= 21 && h1 <= 23) {
                    suenioEnMin = 24 * 60 - enMin1 + enMin2;
                    hSuenio = suenioEnMin / 60;
                    mSuenio = suenioEnMin % 60;
                    sumHras += hSuenio;
                    sumMns += mSuenio;
                }else if (h1 >= 00 && h1 <= 12) {
                    suenioEnMin = enMin2 - enMin1;
                    hSuenio = suenioEnMin / 60;
                    mSuenio = suenioEnMin % 60;
                    sumHras += hSuenio;
                    sumMns += mSuenio;
                }
            }
        }

        //Poner este if dentro de if de arriba
        if (sumMns>=60){
            int divisionHras = sumMns/60;
            sumHras += divisionHras;
            int resto = sumMns%60;
            sumMns = resto;
        }

        tDefinitivo = String.valueOf(sumHras)+ "h "+ String.valueOf(sumMns)+ "min";

        // Poner valor de tDefinitivo en un Toast o en los TextView del principio
        cursorRegistros.close();

        return tDefinitivo;
        //return listaRegistros;
    }

    public double horas_totales_de_suenio_GraphView(String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Registros> listaRegistros = new ArrayList<>();
        Registros registro = null;
        Cursor cursorRegistros = null;

        String query1="SELECT fecha,hora,accion FROM t_registros WHERE "+
                "fecha=(SELECT DATE(fecha,'+1 day') FROM t_registros WHERE fecha='"+ date+ "') "+
                "OR fecha='"+ date+ "'";
        cursorRegistros=db.rawQuery(query1, null);



        if(cursorRegistros.moveToFirst()){
            do{
                registro = new Registros();

                registro.setFecha(cursorRegistros.getString(0));
                registro.setHora(cursorRegistros.getString(1));
                registro.setAccion(cursorRegistros.getString(2));

                listaRegistros.add(registro);
            }while (cursorRegistros.moveToNext());
        }

        int h1, m1, h2, m2, enMin1, enMin2;
        String accion1="";
        String accion2="";
        int suenioEnMin, hSuenio, mSuenio;
        String tDefinitivo="";
        int sumHras=0;
        int sumMns=0;
        for(int i=1;i<listaRegistros.size();i++) {
            h1 = Integer.parseInt(listaRegistros.get(i - 1).getHora().substring(0, 2));
            m1 = Integer.parseInt(listaRegistros.get(i - 1).getHora().substring(3, 5));
            h2 = Integer.parseInt(listaRegistros.get(i).getHora().substring(0, 2));
            m2 = Integer.parseInt(listaRegistros.get(i).getHora().substring(3, 5));
            enMin1 = h1 * 60 + m1;
            enMin2 = h2 * 60 + m2;
            accion1 = listaRegistros.get(i - 1).getAccion();
            accion2 = listaRegistros.get(i).getAccion();

            if (h2 >= 00 && h2 <= 12 && accion1.equals("Conexion") && accion2.equals("Desconexion")) {
                if (h1 >= 21 && h1 <= 23) {
                    suenioEnMin = 24 * 60 - enMin1 + enMin2;
                    hSuenio = suenioEnMin / 60;
                    mSuenio = suenioEnMin % 60;
                    sumHras += hSuenio;
                    sumMns += mSuenio;
                }else if (h1 >= 00 && h1 <= 12) {
                    suenioEnMin = enMin2 - enMin1;
                    hSuenio = suenioEnMin / 60;
                    mSuenio = suenioEnMin % 60;
                    sumHras += hSuenio;
                    sumMns += mSuenio;
                }
            }
        }

        //Poner este if dentro de if de arriba
        if (sumMns>=60){
            int divisionHras = sumMns/60;
            sumHras += divisionHras;
            int resto = sumMns%60;
            sumMns = resto;
        }

        tDefinitivo = String.valueOf(sumHras)+ "h "+ String.valueOf(sumMns)+ "min";

        Double sumHrasDouble = Double.valueOf(sumHras);
        Double sumMnsDouble = Double.valueOf(sumMns);
        double totalHorasGraphView = sumHrasDouble + sumMnsDouble/60.0;
        // Poner valor de tDefinitivo en un Toast o en los TextView del principio
        cursorRegistros.close();

        return totalHorasGraphView;
        //return listaRegistros;
    }

    public ArrayList<Weekday> diasSEMANA(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorRegistros = null;
        Cursor cursorRegistros2 = null;
        String diaLunes="";
        String diaAhora="";
        String dia_prueba="";
        ArrayList<Weekday> listaWeekdays = new ArrayList<>();
        Weekday weekDay ;

        String query1= "SELECT DATE('now','weekday 1','-7 days')";
        cursorRegistros=db.rawQuery(query1, null);
        if(cursorRegistros.moveToFirst()){
            do{
                diaLunes = cursorRegistros.getString(0);
            }while (cursorRegistros.moveToNext());
        }

        String query2= "SELECT DATE('now')";
        cursorRegistros=db.rawQuery(query2, null);
        if(cursorRegistros.moveToFirst()){
            do{
                diaAhora = cursorRegistros.getString(0);
            }while (cursorRegistros.moveToNext());
        }

        String query3= "SELECT fecha, strftime('%w',fecha) AS weekday FROM t_registros WHERE fecha BETWEEN '"+ diaLunes+ "' AND '"+ diaAhora+ "' GROUP BY fecha";
        cursorRegistros=db.rawQuery(query3, null);
        if(cursorRegistros.moveToFirst()){
            do{
                weekDay = new Weekday();
                weekDay.setFecha(cursorRegistros.getString(0));
                weekDay.setWeekday(cursorRegistros.getString(1));

                listaWeekdays.add(weekDay);
            }while (cursorRegistros.moveToNext());
        }
        cursorRegistros.close();

        return listaWeekdays;
    }


}

