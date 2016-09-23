package com.example.renata.sistemaferramentas;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarDadosActivity extends AppCompatActivity {

    EditText edNomeFerramenta, edFabricante, edPreco, edCor, edReferencia;
    Button btAlterar, btFechar;
    SQLiteDatabase db;
    int numReg;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados);

        Bundle b = getIntent().getExtras();

        edNomeFerramenta = (EditText) findViewById(R.id.edNomeFerramenta);
        edFabricante = (EditText) findViewById(R.id.edReferencia);
        edPreco = (EditText) findViewById(R.id.edPreco);
        edCor = (EditText) findViewById(R.id.edCor);
        edReferencia = (EditText) findViewById(R.id.edReferencia);
        btAlterar = (Button) findViewById(R.id.btAlterar);
        btFechar = (Button) findViewById(R.id.btFechar);
        numReg = b.getInt("numreg");
        db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        c = db.query("ferramentas", new String[]{
                        "nome_ferramenta", "fabricante", "preco", "cor", "referencia"}, "numreg =" +
                        " " + numReg,
                null, null, null, null);
        c.moveToFirst();

        edNomeFerramenta.setText(c.getString(0));
        edFabricante.setText(c.getString(1));
        edPreco.setText(c.getString(2));
        edCor.setText(c.getString(3));
        edReferencia.setText(c.getString(4));
        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome_ferramenta = edNomeFerramenta.getText().toString();

                String fabricante = edFabricante.getText().toString();
                String preco = edPreco.getText().toString();
                String cor = edCor.getText().toString();
                String referencia = edReferencia.getText().toString();
                ContentValues valor = new ContentValues();
                valor.put("nome_ferramenta", nome_ferramenta);
                valor.put("fabricante", fabricante);
                valor.put("preco", preco);
                valor.put("cor", cor);
                valor.put("referencia", referencia);

                db.update("ferramentas", valor, "numreg=" + numReg, null);

                AlertDialog.Builder dialogo = new AlertDialog.Builder(AlterarDadosActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Dados atualizados com sucesso!");
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlterarDadosActivity.this.finish();
                    }
                });
                dialogo.show();
            }
        });

        btFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlterarDadosActivity.this.finish();
            }
        });
    }
}
