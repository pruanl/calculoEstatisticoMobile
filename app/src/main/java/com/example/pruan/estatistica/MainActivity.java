package com.example.pruan.estatistica;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtPopulacao;
    private EditText edtProbFavor;
    private EditText edtProbContra;
    private EditText edtConfiabilidade;
    private EditText edtErroEst;
    private EditText textResultado;

    private double finFavor;
    private double finContra;
    private double confiabilidade;
    private double erroEst;
    private double resultadoFinal;
    private int r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPopulacao = (EditText) findViewById(R.id.edtPopulacao);
        edtProbFavor = (EditText) findViewById(R.id.edtProbFavor);
        edtProbContra = (EditText) findViewById(R.id.edtProbContra);
        edtConfiabilidade = (EditText) findViewById(R.id.edtConfiabilidade);
        edtErroEst = (EditText) findViewById(R.id.edtErroEst);
        textResultado = (EditText) findViewById(R.id.textResult);

    }

    protected void calculoProbabilidade(double favor,double contra){
        double num = (favor + contra);

        finFavor = favor/num;
        finContra = contra/num;

    }

    protected double calculoConfiabilidade(double valor){
        if(valor == 90){
            return 1.64;
        }
        else{
            if (valor ==95) {
                return 1.96;
            }
                else{
                    if (valor == 99){
                        return 2.57;
                    }
                    else{
                        return 0;
                    }
                }
        }
    }

    protected double calculoErro(double valor){
       // double num = valor/100;
        return valor/100;
    }



    public void clickOk(View view){
        calculoProbabilidade(Double.parseDouble(edtProbFavor.getText().toString()),Double.parseDouble(edtProbContra.getText().toString()));
        confiabilidade = calculoConfiabilidade(Double.parseDouble(edtConfiabilidade.getText().toString()));
        erroEst = calculoErro(Double.parseDouble(edtErroEst.getText().toString()));

          resultadoFinal = ((Double.parseDouble(edtPopulacao.getText().toString())*finFavor*finContra*(confiabilidade*confiabilidade)))/(finContra*finFavor*(confiabilidade*confiabilidade)+(((Double.parseDouble(edtPopulacao.getText().toString())-1))*(erroEst*erroEst)));
     //   resultadoFinal = (5000*0.35*0.65*1.64*1.64)/(0.35*0.35*1.64*1.64+(4999*(0.04)));
        r = (int)Math.ceil(resultadoFinal);
        textResultado.setText(String.valueOf(r));




    }
}
