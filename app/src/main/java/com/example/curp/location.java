package com.example.curp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;

public class location extends AppCompatActivity {

    Button returnDate;

    private Spinner SEX, STATE;

    private TextInputLayout txtsex,txtstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        returnDate = findViewById(R.id.returnDate);
        STATE = findViewById(R.id.state);
        txtsex = findViewById(R.id.txtsex);
        txtstate = findViewById(R.id.txtstate);
        SEX = findViewById(R.id.sex);
        String[] sexItems = new String[] {"Selecciona el sexo", "Mujer", "Hombre", "No binario"};
        ArrayAdapter<String> adaptersex = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,sexItems);
        SEX.setAdapter(adaptersex);
        String[] statesItems = new String[]{"Selecciona un estado","Aguascalientes","Baja California","Baja California Sur","Campeche","Chiapas","Chihuahua","Ciudad de México",
                "Coahuila","Colima","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","Estado de México","Michoacán","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla",
                "Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz","Yucatán","Zacatecas"};
        ArrayAdapter<String> adapterstate = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,statesItems);
        STATE.setAdapter(adapterstate);
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed();}
        });
    }

    public  void resultView(View v){
        boolean res = validateLocation();

            if (res){
                try {
                Intent f = new Intent(location.this, resultate.class);
                Bundle getdata = getIntent().getExtras();
                ArrayList <String> infoLocation = new ArrayList<>();
                String[] info = getdata.getStringArray("updateData");
                Collections.addAll(infoLocation,info);
                String sex = SEX.getSelectedItem().toString();
                String state = STATE.getSelectedItem().toString();
                infoLocation.add(sex);
                infoLocation.add(state);
                f.putStringArrayListExtra("updateData",infoLocation);
                startActivity(f);
            }catch (Exception e){
                e.printStackTrace();
            }
            }

    }

    public boolean validateLocation(){
        boolean validation = true;

        String sex = SEX.getSelectedItem().toString();
        String state = STATE.getSelectedItem().toString();

        if (sex.equals("Selecciona el sexo")){
            txtsex.setError("Seleccione la opcion por favor");
            validation = false;
        }else{
            txtsex.setErrorEnabled(false);
        }

        if (state.equals("Selecciona un estado")){
            txtstate.setError("Selecciona la opcion por favor");
            validation = false;
        }else{
            txtstate.setErrorEnabled(false);
        }
        return validation;
    }
}