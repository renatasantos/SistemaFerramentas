package com.example.renata.sistemaferramentas;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExcluirFerramentaActivity extends AppCompatActivity {

    TextView txtNomeFerramenta, txtFabricante, txtPreco, txtCor, txtReferencia;
    Button btFechar, btExcluirFerramenta;
    SQLiteDatabase db;
    Cursor c;
    int numReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_ferramenta);

        Bundle b = getIntent().getExtras();

        txtNomeFerramenta = (TextView) findViewById(R.id.txtNomeFerramenta);
        txtFabricante = (TextView) findViewById(R.id.txtFabricante);
        txtPreco = (TextView) findViewById(R.id.txtPreco);
        txtCor = (TextView) findViewById(R.id.txtCor);
        txtReferencia = (TextView) findViewById(R.id.txtReferencia);
        btFechar = (Button) findViewById(R.id.btFechar);
        btExcluirFerramenta = (Button)findViewById(R.id.btExcluirFerramenta);
        numReg = b.getInt("numreg");

        db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        c = db.query("ferramentas", new String[]{
                "nome_ferramenta", "fabricante", "preco", "cor", "referencia"}, "numreg = " + numReg,
                null, null, null, null);

        c.moveToFirst();

        txtNomeFerramenta.setText(c.getString(0));
        txtFabricante.setText(c.getString(1));
        txtPreco.setText(c.getString(2));
        txtCor.setText(c.getString(3));
        txtReferencia.setText(c.getString(4));

        btExcluirFerramenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("ferramentas", "numreg=" + numReg, null);

                AlertDialog.Builder dialogo = new AlertDialog.Builder(ExcluirFerramentaActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Dados excluidos com sucesso!");
                dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExcluirFerramentaActivity.this.finish();
                    }
                });
                dialogo.show();
            }
        });
        btFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExcluirFerramentaActivity.this.finish();
            }
        });
    }
}
