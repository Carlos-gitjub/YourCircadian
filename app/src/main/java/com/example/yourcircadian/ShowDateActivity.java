package com.example.yourcircadian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourcircadian.adaptadores.listaRegistrosAdapter;
import com.example.yourcircadian.db.DbRegistros;
import com.example.yourcircadian.entidades.Registros;

import java.util.ArrayList;

public class ShowDateActivity extends AppCompatActivity {

    RecyclerView listaRegistros;
    ArrayList<Registros> listaArrayRegistros;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_layout);

        listaRegistros = findViewById(R.id.listaRegistros);
        listaRegistros.setLayoutManager(new LinearLayoutManager(this));

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");

        DbRegistros dbRegistros = new DbRegistros(ShowDateActivity.this);

        listaArrayRegistros = new ArrayList<>();

        //listaRegistrosAdapter adapter = new listaRegistrosAdapter(dbRegistros.horas_totales_de_suenio(date));
        //listaRegistros.setAdapter(adapter);
    }
}
