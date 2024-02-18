package com.example.curp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class dateView extends AppCompatActivity {

    private Spinner DAY, MONTH;
    private TextInputEditText YEARTXT;
    private TextInputLayout yeartext,daytext,monthtxt;

     Button MainReturn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_view);

        DAY = findViewById(R.id.day);
        daytext = findViewById(R.id.txtday);
        MONTH = findViewById(R.id.month);
        monthtxt = findViewById(R.id.txtmonth);
        YEARTXT = findViewById(R.id.year);
        yeartext = findViewById(R.id.txtyear);
        MainReturn = findViewById(R.id.returnMain);
        ArrayList<String> dayNumbers = new ArrayList<>();
        for (int i = 0; i <= 31; i++) {
            String day = String.format("%02d", i);
            dayNumbers.add(day);
        }
        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dayNumbers);
        DAY.setAdapter(adapterDay);

        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            String month = String.format(Locale.getDefault(), "%02d",i);
            monthList.add(month);
        }
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthList);
        MONTH.setAdapter(adapterMonth);

        MainReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void changeViewLocation(View v){
        try {
            boolean res = selectionValidate();
            if (res){
                Intent a = new Intent(dateView.this , location.class);
                Bundle getData = getIntent().getExtras();
                ArrayList <String> info = new ArrayList<>();
                String[] data = getData.getStringArray("Data");
                Collections.addAll(info,data);
                String day = DAY.getSelectedItem().toString();
                String month = MONTH.getSelectedItem().toString();
                String year = YEARTXT.getText().toString();
                info.add(day);
                info.add(month);
                info.add(year);
                a.putStringArrayListExtra("updateData", info);
                startActivity(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean selectionValidate() {
        boolean validation = true;
        String day = DAY.getSelectedItem().toString();
        String month = MONTH.getSelectedItem().toString();
        String year = YEARTXT.getText().toString();
        if (year.isEmpty()){
            yeartext.setError("Por favor rellene este campo");
            validation = false;
        }else{
            yeartext.setErrorEnabled(false);
        }

        if (day.equals("00")){
            daytext.setError("Elija un campo correcto porfavor");
            validation = false;
        }else{
            daytext.setErrorEnabled(false);
        }

        if (month.equals("00")){
            monthtxt.setError("Elija un campo correcto por favor");
            validation = false;
        }else{
            monthtxt.setErrorEnabled(false);
        }
        return validation;
    }

}
