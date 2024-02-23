package com.example.curp;

import static java.lang.Math.round;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class resultate extends AppCompatActivity {
    private String name, lastnameP, lastnameM, day, mont, year, sex, state, abrev;

    private String firstLastNameP, firstVocalP, namef, names, namet, secondLastNameM, yearT, genV,twolastnameP,twolastnameM,twoNameCo,treeConsonantName;
    private TextView nam, lastP, lastM, dayt, montx, yeart, gen, sta, curp;

    private boolean twoName = false, treeName = false,horn = false, printCurp = false;

    Button exitboton, newseach;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultate);

        nam = findViewById(R.id.nameUsertxt);
        lastP = findViewById(R.id.lastNamePtxt);
        lastM = findViewById(R.id.lastnameMtxt);
        dayt = findViewById(R.id.daytxt);
        montx = findViewById(R.id.monthTxt);
        yeart = findViewById(R.id.yearTxt);
        gen = findViewById(R.id.gentxt);
        sta = findViewById(R.id.stateTxt);
        curp = findViewById(R.id.finalResult);
        exitboton = findViewById(R.id.exitbtn);
        newseach = findViewById(R.id.SearchNew);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button returnLoca = (Button) findViewById(R.id.returnLocation);

        returnLoca.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onBackPressed();}});

        exitboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeApplication();
            }
        });

        newseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nwSh();
            }
        });

        Bundle receivedData = getIntent().getExtras();
        if (receivedData != null) {
            String[] datos = receivedData.getStringArray("Data");
            if (datos != null && datos.length >= 3) {
                name = datos[0];
                name = name.toUpperCase(Locale.getDefault());
                if (!name.isEmpty()) {
                    char[] nameSearch = name.toCharArray();
                    boolean espacioEncontrado = false;
                    ArrayList<Character> nameLetters = new ArrayList<>();
                    nameLetters.add(nameSearch[0]);
                    ArrayList<Character> consonantsname = new ArrayList<>();
                    for (char valor :nameSearch) {
                        if (valor != 'A' && valor != 'E' && valor != 'I' && valor != 'O' && valor != 'U' && valor != 'Ñ'){
                            consonantsname.add(valor);
                        }
                        Log.d("Consonants", "Letras consonantes: "+consonantsname);
                    }
                    if (consonantsname.isEmpty()){
                        printCurp = true;
                        }else {
                        twoNameCo = String.valueOf(consonantsname.get(1));
                    }


                    for (char valor : nameSearch) {
                        if (valor == ' ') {
                            espacioEncontrado = true;
                        } else if (espacioEncontrado) {
                            nameLetters.add(valor);
                            espacioEncontrado = false;
                        }
                    }
                    if (nameLetters.size() == 3) {
                        namef = String.valueOf(nameLetters.get(0));
                        names = String.valueOf(nameLetters.get(1));
                        namet = String.valueOf(nameLetters.get(2));
                        treeName = true;
                    } else if (nameLetters.size() == 2) {
                        namef = String.valueOf(nameLetters.get(0));
                        names = String.valueOf(nameLetters.get(1));
                        twoName = true;
                    } else {
                        namef = String.valueOf(nameLetters.get(0));
                    }
                    Log.d("numero de nombres", "namese: "+nameLetters.size());
                }
                lastnameP = datos[1];
                lastnameP = lastnameP.toUpperCase(Locale.getDefault());

                if (!lastnameP.isEmpty()) {
                    char[] lastPSearch = lastnameP.toCharArray();
                    ArrayList<Character> consonants = new ArrayList<>();
                    ArrayList<Character> vocalsP = new ArrayList<>();
                    for (char valor : lastPSearch) {
                        if ('H' == valor || 'Ñ' == valor){
                            horn = true;
                            continue;
                        }
                            if (valor != 'A' && valor != 'E' && valor != 'I' && valor != 'O' && valor != 'U') {
                                consonants.add(valor);
                            } else {
                                vocalsP.add(valor);
                            }

                    }
                    Log.d("ConsonantsP", "consonantesP "+consonants);
                    Log.d("VocalssP", "vovalssP "+vocalsP);

                    if (consonants.isEmpty() || vocalsP.isEmpty()){
                        printCurp = true;
                    }else{
                        if (horn){
                            firstLastNameP = String.valueOf(vocalsP.get(0));
                            firstVocalP = String.valueOf(consonants.get(0));
                            twolastnameP = String.valueOf(consonants.get(0));
                        }else{
                            firstLastNameP = String.valueOf(consonants.get(0));
                            twolastnameP = String.valueOf(consonants.get(1));
                            firstVocalP = String.valueOf(vocalsP.get(0));
                        }
                    }
                }

                lastnameM = datos[2];
                lastnameM = lastnameM.toUpperCase(Locale.getDefault());
                if (treeName) {
                    secondLastNameM = namet;
                } else {
                    char[] lastMseach = lastnameM.toCharArray();
                    ArrayList<Character> consonantsM = new ArrayList<>();
                    for (char valor : lastMseach) {
                        if (valor != 'A' && valor != 'E' && valor != 'I' && valor != 'O' && valor != 'U') {
                            consonantsM.add(valor);
                        }
                    }
                    if (consonantsM.isEmpty()){
                        printCurp = true;
                    }else {
                        secondLastNameM = String.valueOf(consonantsM.get(0));
                        twolastnameM = String.valueOf(consonantsM.get(1));
                    }
                }

                nam.setText(name);
                lastP.setText(lastnameP);
                lastM.setText(lastnameM);
            }
            if (receivedData.containsKey("Day")) {
                day = receivedData.getString("Day");
                dayt.setText(day);
            }
            if (receivedData.containsKey("Sex")) {
                sex = receivedData.getString("Sex");
                gen.setText(sex);
                if ("Mujer".equals(sex)) {
                    genV = "M";
                } else if("No binario".equals(sex)){
                    genV = "X";
                }else {
                    genV = "H";
                }
            }

            if (receivedData.containsKey("Month")) {
                mont = receivedData.getString("Month");
                montx.setText(mont);
            }
            if (receivedData.containsKey("Year")) {
                year = receivedData.getString("Year");
                yeart.setText(year);
                yearT = year.substring(2);
            }
            if (receivedData.containsKey("State")) {
                state = receivedData.getString("State");
                sta.setText(state);
            }
            if (receivedData.containsKey("Abrev")) {
                abrev = receivedData.getString("Abrev");
            }

            StringBuilder abecedario = new StringBuilder();
            char letra = 'a';
            for (int i= 0; i<26;i++){
                abecedario.append(letra);
                letra++;
            }

            int numberAla = (int) Math.round(Math.random() * 25);
            char letterAbc = abecedario.charAt(numberAla);
            letterAbc = Character.toUpperCase(letterAbc);
            int numberHomo = (int) round(Math.random()*9);
            try {
                if (!printCurp) {
                    if (twoName) {
                        curp.setText(firstLastNameP + " " + firstVocalP + " " + secondLastNameM + " " + namef + " " + yearT + " " + mont + " " + day + " " + genV + " " + abrev + " " + twolastnameP + " " + twolastnameM + " " + twoNameCo + " " + letterAbc + " " + numberHomo);
                        twoName = false;
                    } else {
                        curp.setText(firstLastNameP + " " + firstVocalP + " " + secondLastNameM + " " + namef + " " + yearT + " " + mont + " " + day + " " + genV + " " + abrev + " " + twolastnameP + " " + twolastnameM + " " + twoNameCo + " " + letterAbc + " " + numberHomo);
                    }
                }else{
                    returnActivity();
                    printCurp = false;
                }
            }catch (Exception e){
                Log.d("Insersion", "Error al establecer los datos: "+e);
            }
        } else {
            Log.d("Sin datos", "No se recibieron datos en la nueva pantalla.");
        }

    }

    private void closeApplication() {
        finishAffinity();
        System.exit(0);
    }

    public void returnActivity(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Error no se cumplen con los caracteres nesesarios por favor ingrese la informacion completa")
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    Intent main = new Intent(resultate.this, MainActivity.class);
                    startActivity(main);
                    finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void nwSh(){
        Intent main = new Intent(resultate.this, MainActivity.class);
        startActivity(main);
        finish();
    }
}
