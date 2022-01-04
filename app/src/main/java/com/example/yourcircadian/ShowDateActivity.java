package com.example.yourcircadian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowDateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_layout);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");

        TextView textView;
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(date));
    }
}
