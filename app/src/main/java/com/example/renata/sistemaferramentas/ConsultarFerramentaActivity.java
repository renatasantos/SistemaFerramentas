package com.example.renata.sistemaferramentas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConsultarFerramentaActivity extends AppCompatActivity {

    TextView txtNomeFerramenta, txtFabricante, txtPreco, txtCor, txtReferencia;
    Button btFechar;
    SQLiteDatabase db;
    Cursor c;
    int numReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ferramenta);

        Bundle b = getIntent().getExtras();

        txtNomeFerramenta = (TextView) findViewById(R.id.txtNomeFerramenta);
        txtFabricante = (TextView) findViewById(R.id.txtFabricante);
        txtPreco = (TextView) findViewById(R.id.txtPreco);
        txtCor = (TextView) findViewById(R.id.txtCor);
        txtReferencia = (TextView) findViewById(R.id.txtReferencia);
        btFechar = (Button) findViewById(R.id.btFechar);
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

        btFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarFerramentaActivity.this.finish();
            }
        });
    }
}
