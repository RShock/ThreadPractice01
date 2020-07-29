package com.example.threadpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.thread_activity_btn);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
            startActivity(intent);
        });
    }
}