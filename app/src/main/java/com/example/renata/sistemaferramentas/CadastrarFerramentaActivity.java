package com.example.renata.sistemaferramentas;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarFerramentaActivity extends AppCompatActivity {

    EditText edNomeFerramenta, edFabricante, edPreco, edCor, edReferencia;
    Button btCadastrar, btFechar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_ferramenta);

        edNomeFerramenta = (EditText)findViewById(R.id.edNomeFerramenta);
        edFabricante = (EditText)findViewById(R.id.edFabricante);
        edPreco = (EditText)findViewById(R.id.edPreco);
        edCor = (EditText)findViewById(R.id.edCor);
        edReferencia = (EditText)findViewById(R.id.edReferencia);

        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeFerramenta = edNomeFerramenta.getText().toString();
                String fabricante = edFabricante.getText().toString();
                String preco = edPreco.getText().toString();
                String cor = edCor.getText().toString();
                String referencia = edReferencia.getText().toString();

                ContentValues valor = new ContentValues();

                valor.put("nome_ferramenta", nomeFerramenta);
                valor.put("fabricante", fabricante);
                valor.put("preco", preco);
                valor.put("cor", cor);
                valor.put("referencia", referencia);

                db.insert("ferramentas", null, valor);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarFerramentaActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Dados Cadastrados com Sucesso");
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        CadastrarFerramentaActivity.this.finish();
                    }
                });
                dialogo.show();
            }
        });

        btFechar = (Button)findViewById(R.id.btFechar);
        btFechar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                CadastrarFerramentaActivity.this.finish();
            }
        });

        try{
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        }
        catch (Exception e){
            MostraMensagem("Erro " + e.toString());
        }
    }
    public void MostraMensagem(String str){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarFerramentaActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
