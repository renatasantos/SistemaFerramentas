package com.example.renata.sistemaferramentas;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipalActivity extends AppCompatActivity {

    SQLiteDatabase db;

    Button btCadastrarFerramenta;
    Button btConsultarFerramenta;
    Button btAlterarDados;
    Button btExcluirFerramenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btCadastrarFerramenta = (Button)findViewById(R.id.btCadastrarFerramenta);
        btConsultarFerramenta = (Button)findViewById(R.id.btConsultarFerramenta);
        btAlterarDados = (Button)findViewById(R.id.btAlterarDados);
        btExcluirFerramenta = (Button)findViewById(R.id.btExcluirFerramenta);

        btCadastrarFerramenta.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0){

                Intent cadastrarFerramentaActivity = new Intent(MenuPrincipalActivity.this, CadastrarFerramentaActivity.class);
                MenuPrincipalActivity.this.startActivity(cadastrarFerramentaActivity);
                }
        });

        btAlterarDados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent consultaFerramentaActivity = new Intent(MenuPrincipalActivity.this, BuscaFerramentaActivity.class);
                consultaFerramentaActivity.putExtra("opcaoDados", 1);
                MenuPrincipalActivity.this.startActivity(consultaFerramentaActivity);
            }
        });

        btConsultarFerramenta.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent consultarFerramentaActivity = new Intent(MenuPrincipalActivity.this, BuscaFerramentaActivity.class);
                consultarFerramentaActivity.putExtra("opcaoDados", 2);
                MenuPrincipalActivity.this.startActivity(consultarFerramentaActivity);
            }
        });

        btExcluirFerramenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent consultarFerramentaActivity = new Intent(MenuPrincipalActivity.this, BuscaFerramentaActivity.class);
                consultarFerramentaActivity.putExtra("opcaoDados", 3);
                MenuPrincipalActivity.this.startActivity(consultarFerramentaActivity);
            }
        });

        try {
            db = openOrCreateDatabase("bancoDados", Context.MODE_PRIVATE, null);
            db.execSQL("create table if not exists" + "ferramentas(numreg integer primary key" + "autoincrement, nome_ferramenta text not null, " + "fabricante text not null, " + "preco float not null, " + "cor text not null, " + "referencia text not null)");
        }
        catch (Exception e){
            MostraMensagem("Erro "+e.toString());
        }
    }
    public void MostraMensagem(String str){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MenuPrincipalActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
