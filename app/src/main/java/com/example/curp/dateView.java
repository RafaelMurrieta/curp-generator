package com.example.curp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Locale;

public class dateView extends AppCompatActivity {

    private Spinner DAY, MONTH;
    private TextInputEditText YEARTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_view);
        Bundle getData = getIntent().getExtras();
        String[] info = getData.getStringArray("Data");

        DAY = findViewById(R.id.day);
        MONTH = findViewById(R.id.month);
        YEARTXT = findViewById(R.id.year);

        ArrayList<String> dayNumbers = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            String day = String.format("%02d", i);
            dayNumbers.add(day);
        }
        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dayNumbers);
        DAY.setAdapter(adapterDay);

        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String month = String.format(Locale.getDefault(), "%02d",i);
            monthList.add(month);
        }
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthList);
        MONTH.setAdapter(adapterMonth);
    }

    public void selectionValidate() {
        String day = DAY.getSelectedItem().toString();
        String month = MONTH.getSelectedItem().toString();
        String year = YEARTXT.getText().toString();

        if (day.isEmpty() || month.isEmpty() || year.isEmpty()) {
            Toast.makeText(this, "Seleccione todos los campos por favor", Toast.LENGTH_SHORT).show();
        }
    }
}
