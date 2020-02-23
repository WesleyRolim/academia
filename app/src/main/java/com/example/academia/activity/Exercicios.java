package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.academia.R;


public class Exercicios extends AppCompatActivity {

    private Spinner musculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        musculos = findViewById(R.id.gupoMuscularesSpinner);
        


    }
}
