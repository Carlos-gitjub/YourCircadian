package com.example.yourcircadian.db;
import static java.lang.Math.round;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.yourcircadian.entidades.Registros;
import com.example.yourcircadian.entidades.Weekday;

import java.util.ArrayList;
import java.util.Iterator;

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

    @Override
    public String horaALaQueSeLevanta() {
        SQLiteDatabase db = this.getWritableDatabase();
        String hora_con_segundos = null;
        String hora="";
        Cursor cursorRegistros = null;
        String query = "SELECT hora FROM t_registros "+
        "WHERE id = (SELECT MAX(id) FROM t_registros WHERE (hora < '12:00:00') AND accion = 'Desconexion' "+
        "AND fecha = (SELECT DATE('now')))";

        cursorRegistros = db.rawQuery(query, null);
        if(cursorRegistros.moveToFirst()){
           do{
                hora_con_segundos = cursorRegistros.getString(0);
           }while (cursorRegistros.moveToNext());
        }else{
            cursorRegistros.close();
            return hora;
        }
        cursorRegistros.close();

        hora = "Hoy te levantastes a las "+ hora_con_segundos.substring(0,5);

        return hora;
    }

    @Override
    public String horaALaQueSeAcuesta() {
        SQLiteDatabase db = this.getWritableDatabase();
        String hora_con_segundos = null;
        String hora="";
        Cursor cursorRegistros = null;
        String query = "SELECT hora FROM t_registros "+
        "WHERE id = (SELECT MAX(id) FROM t_registros WHERE ((accion = 'Conexion' AND (hora < '12:00:00') "+
                "AND (fecha = (SELECT DATE('now')))) "+
        "OR (accion = 'Conexion' AND (hora > '21:00:00') "+
                "AND (fecha = (SELECT DATE('now','-1 days'))))))";

        cursorRegistros = db.rawQuery(query, null);
        if(cursorRegistros.moveToFirst()){
            do{
                hora_con_segundos = cursorRegistros.getString(0);
            }while (cursorRegistros.moveToNext());
        }else{
            cursorRegistros.close();
            return hora;
        }
        cursorRegistros.close();

        hora = "Anoche te acostastes a las "+ hora_con_segundos.substring(0,5);

        return hora;
    }

    public String horasTotalesDeSuenio(String date){
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

    public double horasTotalesDeSuenioGraphView(String date){
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

        Double sumHrasDouble = Double.valueOf(sumHras);
        Double sumMnsDouble = Double.valueOf(sumMns);
        double totalHorasGraphView = sumHrasDouble + sumMnsDouble/60.0;
        cursorRegistros.close();
        // Redondea el valor del double a un decimal
        totalHorasGraphView = Math.round(totalHorasGraphView * 10.0) / 10.0;

        return totalHorasGraphView;
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

        //Obtiene el último lunes
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

    public double[] diasMesDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorRegistros = null;
        ArrayList<String> fechas = new ArrayList<>();
        /*
        El plan es tomar todas las fechas registradas del mes correspondiente con un GROUP BY y
        guardarlas en un ArrayList para que después se pasen a la función horas_totales_de_suenio
        y se guarden las horas en un arraylist de tamaño 31 que contendrá un null en la posición
        del día que no tenga horas calculadas.
        */

        String query = "SELECT DISTINCT fecha FROM t_registros WHERE strftime('%m', fecha) = (SELECT strftime('%m','now')) AND strftime('%Y',fecha)=(SELECT strftime('%Y','now'))";
        cursorRegistros = db.rawQuery(query,null);
        if(cursorRegistros.moveToFirst()){
            do{
                fechas.add(cursorRegistros.getString(0));
            }while(cursorRegistros.moveToNext());
        }

        //CORREGIR linea 400. El indice dado a fechas esta fuera de rango.
        String[] dias = new String[31];
        for(int i=1;i<32;i++){
            for(int j=0;j<fechas.size();j++) {
                if (i == Integer.valueOf(fechas.get(j).substring(8, 10))) {
                    dias[i-1] = fechas.get(j).substring(8, 10);
                }
            }
        }
        //pasamos a double los valores string
        Iterator it = fechas.iterator();
        double[] dias_double = new double[31];
        for(int i=0;i<dias.length;i++){
            if(dias[i] != null) {
                if (it.hasNext()) {
                    String f = (String) it.next();
                    dias_double[i] = horasTotalesDeSuenioGraphView(f);
                }
           }
        }

        cursorRegistros.close();
        return dias_double;
    }

    public String mediaNumericaMensual(){
        double[] horas_31_fechas = diasMesDB();

        int contador = 0;
        double sumatorio = 0;
        double media = 0;
        for (int i = 0; i < 31; i++) {
            double horas = horas_31_fechas[i];
            if ( horas != 0.0) {
                contador++;
                sumatorio += horas_31_fechas[i];
            }
        }
        media = sumatorio / contador;
        media = Math.round(media * 10.0) / 10.0;
        String media_string = String.valueOf(media);
        return media_string;
    }

}

