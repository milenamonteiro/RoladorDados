package com.example.rolardados;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
    ArrayList<EditText> etnumlados = new ArrayList<>();
    ArrayList<EditText> etvezes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    boolean boolmod;

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.positivo:
                if (checked)
                    boolmod = true;
                break;
            case R.id.negativo:
                if (checked)
                    boolmod = false;
                break;
        }
    }

    public void verificarDados(View view){
        EditText etnumlados = (EditText)findViewById(R.id.lados);
        EditText etvezes = (EditText)findViewById(R.id.vezes);
        EditText etvalormod = (EditText)findViewById(R.id.modf);
        RadioGroup rgroup = (RadioGroup)findViewById(R.id.radio);

        String numlados = etnumlados.getText().toString(), vezes = etvezes.getText().toString(), valormod = etvalormod.getText().toString();

        if(numlados.isEmpty() || vezes.isEmpty() || valormod.isEmpty() || rgroup.getCheckedRadioButtonId() == -1){
            Dialog dialog = createDialog();
            dialog.show();
        }
        else{
            rolarDado();
        }
    }

    public void rolarDado(){
        EditText etnumlados = (EditText)findViewById(R.id.lados);
        EditText etvezes = (EditText)findViewById(R.id.vezes);
        EditText etvalormod = (EditText)findViewById(R.id.modf);

        double numlados = Double.parseDouble(etnumlados.getText().toString());
        Integer vezes = Integer.parseInt(etvezes.getText().toString());
        Integer valormod = Integer.parseInt(etvalormod.getText().toString());

        double min = 1;
        int result, resultmod;
        vezes++;

        String resultados = new String();

        for (int i = 1; i <= vezes; i++)
        {
            min = Math.ceil (1);
            numlados = Math.floor (numlados);
            result = (int) (Math.floor (Math.random () * (numlados)) + min);
            resultados += String.format("\n\nResultado %o = %o%n", i, result, valormod);
            if(boolmod){
                resultmod = result + valormod;
                resultados += String.format("Modificador = + %o%nFinal = %o", valormod, resultmod);
            }
            else{
                resultmod = result - valormod;
                resultados += String.format("Modificador = - %o%nFinal = %o", valormod, resultmod);
            }
        }

        Intent sendIntent = new Intent(MainActivity.this, ResultActivity.class);
        sendIntent.putExtra(Intent.EXTRA_TEXT, resultados);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Preencha todos os campos!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        return builder.create();
    }


}