package com.example.curp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText lastNameP;
    private EditText lastNameM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ChangeViewDates(View v) {
        try {
            Intent i = new Intent(MainActivity.this, dateView.class);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ComprobateNames(names, lastNP,lastNM){

    }

}