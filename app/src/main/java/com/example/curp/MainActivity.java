package com.example.curp;

import static com.example.curp.R.id.lastNameM;
import static com.example.curp.R.id.nametext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText nametext;
    private EditText lastNamePtext;
    private EditText lastNameMtext;


    private TextInputLayout txtnameinput, txtnamelastP, txtnamelastM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nametext = findViewById(R.id.nametext);
        lastNamePtext = findViewById(R.id.lastNamePtext);
        lastNameMtext = findViewById(R.id.lastNameMtext);
        txtnameinput = findViewById(R.id.namet);
        txtnamelastP = findViewById(R.id.lastNameP);
        txtnamelastM = findViewById(R.id.lastNameM);
        configureTextWatchers();
    }
    private void configureTextWatchers() {
        nametext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtnameinput.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        lastNamePtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtnamelastP.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        lastNameMtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtnamelastM.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    public void ChangeViewDates(View v) {
        String name = nametext.getText().toString();
        String lastNameP = lastNamePtext.getText().toString();
        String lastNameM = lastNameMtext.getText().toString();
        name = quitaAcentos(name);
        lastNameP = quitaAcentos(lastNameP);
        lastNameM = quitaAcentos(lastNameM);

            try {
                boolean campos = validate();
                if (campos){
                    Bundle SendData = new Bundle();
                    String[] datos = new String[]{name,lastNameP,lastNameM};
                    SendData.putStringArray("Data",datos);
                    Intent i = new Intent(MainActivity.this, dateView.class);
                    i.putExtras(SendData);
                    startActivity(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
    }

    private boolean validate(){
        boolean valor = true;
        String nameC, lastnamePC, lastnameMC;
        nameC = nametext.getText().toString();
        lastnamePC = lastNamePtext.getText().toString();
        lastnameMC = lastNameMtext.getText().toString();

        if (nameC.isEmpty()){
            txtnameinput.setError("Ingrese su nombre por favor");
            valor = false;
        }else{
            txtnameinput.setErrorEnabled(false);
        }
        if (lastnamePC.isEmpty()){
            txtnamelastP.setError("Ingrese su apellido paterno por favor");
            valor = false;
        }else{
            txtnamelastP.setErrorEnabled(false);
        }

        if (lastnameMC.isEmpty()){
            txtnamelastM.setError("Ingrese su apellido materno por favor");
            valor = false;
        }else{
            txtnamelastM.setErrorEnabled(false);
        }
        return  valor;
    }

    public static  String quitaAcentos(String texto){
        String txtnomal = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return  patron.matcher(txtnomal).replaceAll("");
    }
}
