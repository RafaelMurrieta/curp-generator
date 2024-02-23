package com.example.curp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class location extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button returnDate;

    private Spinner SEX, STATE;

    private TextInputLayout txtsex, txtstate;

    private Bundle combinedData;

    HashMap<String, String> abreviaturasEstados = new HashMap<>();

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

        // Inicializa el HashMap abreviaturasEstados aquí
        abreviaturasEstados.put("Aguascalientes","AS");
        abreviaturasEstados.put("Baja California","BC");
        abreviaturasEstados.put("Baja California Sur","BS");
        abreviaturasEstados.put("Campeche","CC");
        abreviaturasEstados.put("Coahuila","CL");
        abreviaturasEstados.put("Colima","CM");
        abreviaturasEstados.put("Chiapas","CS");
        abreviaturasEstados.put("Chihuahua","CH");
        abreviaturasEstados.put("Ciudad de México","DF");
        abreviaturasEstados.put("Durango","DG");
        abreviaturasEstados.put("Guanajajuato","GT");
        abreviaturasEstados.put("Guerrero","GR");
        abreviaturasEstados.put("Hidalgo","HG");
        abreviaturasEstados.put("Jalisco","JC");
        abreviaturasEstados.put("Estado de México","MC");
        abreviaturasEstados.put("Michoacán","MN");
        abreviaturasEstados.put("Morelos","MS");
        abreviaturasEstados.put("Nayarit","NT");
        abreviaturasEstados.put("Nuevo León","NL");
        abreviaturasEstados.put("Oaxaca","OC");
        abreviaturasEstados.put("Puebla","PL");
        abreviaturasEstados.put("Querétaro","QT");
        abreviaturasEstados.put("Quintana Roo","QR");
        abreviaturasEstados.put("San Luis Potosí","SP");
        abreviaturasEstados.put("Sinaloa","SL");
        abreviaturasEstados.put("Sonora","SR");
        abreviaturasEstados.put("Tabasco","TC");
        abreviaturasEstados.put("Tamaulipas","TS");
        abreviaturasEstados.put("Tlaxcala","TL");
        abreviaturasEstados.put("Veracruz","VZ");
        abreviaturasEstados.put("Yucatán","VN");
        abreviaturasEstados.put("Zacatecas","ZS");

        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle getData = getIntent().getExtras();
        if (getData != null) {
            combinedData = new Bundle(getData);
        } else {
            combinedData = new Bundle();
        }
    }

    public void resultView(View v) {
        boolean res = validateLocation();

        if (res) {
            try {
                Intent f = new Intent(location.this, resultate.class);
                String sex = SEX.getSelectedItem().toString();
                String state = STATE.getSelectedItem().toString();
                String abreviatura = abreviaturasEstados.get(state);
                combinedData.putString("Sex", sex);
                combinedData.putString("State", state);
                combinedData.putString("Abrev",abreviatura);
                f.putExtras(combinedData);
                startActivity(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String estadoSeleccionado = parent.getItemAtPosition(position).toString();
        String abreviatura = abreviaturasEstados.get(estadoSeleccionado);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean validateLocation() {
        boolean validation = true;

        String sex = SEX.getSelectedItem().toString();
        String state = STATE.getSelectedItem().toString();

        if (sex.equals("Selecciona el sexo")) {
            txtsex.setError("Seleccione la opcion por favor");
            validation = false;
        } else {
            txtsex.setErrorEnabled(false);
        }

        if (state.equals("Selecciona un estado")) {
            txtstate.setError("Selecciona la opcion por favor");
            validation = false;
        } else {
            txtstate.setErrorEnabled(false);
        }
        return validation;
    }

}
