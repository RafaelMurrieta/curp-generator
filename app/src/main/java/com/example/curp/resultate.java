package com.example.curp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class resultate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultate);
        Bundle geTinfo = getIntent().getExtras();
        ArrayList<String> receivedData = geTinfo.getStringArrayList("updateData");

        if (receivedData != null) {
            for (String valor : receivedData) {
                Log.d("Valor", "Valor: " + valor);
            }
        } else {
            Log.d("No esta lleno", "No se recibieron datos en el Intent.");
        }

    }

    public void resultFinal(){

    }
}