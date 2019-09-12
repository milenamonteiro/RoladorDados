package com.example.rolardados;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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
        EditText etnumlados = findViewById(R.id.lados);
        EditText etvezes = findViewById(R.id.vezes);
        EditText etvalormod = findViewById(R.id.modf);
        RadioGroup rgroup = findViewById(R.id.radio);

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
        EditText etnumlados = findViewById(R.id.lados);
        EditText etvezes = findViewById(R.id.vezes);
        EditText etvalormod = findViewById(R.id.modf);

        double numlados = Double.parseDouble(etnumlados.getText().toString());
        Integer vezes = Integer.parseInt(etvezes.getText().toString());
        Integer valormod = Integer.parseInt(etvalormod.getText().toString());

        double min = 1;
        int result, resultmod, somaresults = 0;
        List<Integer> valores = new ArrayList<>();

        String resultados, tipomod = "";

        for (int i = 1; i <= vezes; i++)
        {
            min = Math.ceil (1);
            numlados = Math.floor (numlados);
            result = (int) (Math.floor (Math.random () * (numlados)) + min);
            if(boolmod){
                resultmod = result + valormod;
                tipomod = "+";
            }
            else{
                resultmod = (result - valormod);
                tipomod = "-";
            }
            somaresults += resultmod;
            valores.add(resultmod);
        }
        resultados = String.format("%nDado%n%sd%s%s%s%n%nFinal%n%s%n%nValores%n", vezes, (int)numlados, tipomod, valormod, somaresults);

        StringBuilder sb = new StringBuilder();

        for(int a : valores){
            sb.append(a).append("; ");
        }

        resultados += (sb.toString());

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