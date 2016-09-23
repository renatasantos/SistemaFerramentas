package com.example.renata.sistemaferramentas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import adapter.DadosFerramenta;
import adapter.MyCustomRowBaseAdapter;

import static com.example.renata.sistemaferramentas.R.id.lstResultadoBusca;

public class BuscaFerramentaActivity extends AppCompatActivity {

    Spinner spnOpcoes;
    LinearLayout layoutCampoBusca;
    EditText edPalavraChave;
    ListView lstResultaBusca;
    Button btBuscar;
    int opcaoBusca, opcaoDados, numReg;
    SQLiteDatabase db;
    Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_ferramenta);

        spnOpcoes = (Spinner) findViewById(R.id.spnOpcoes);
        layoutCampoBusca = (LinearLayout) findViewById(R.id.layoutCampoBusca);
        edPalavraChave = (EditText) findViewById(R.id.edPalavraChave);
        lstResultadoBusca = (ListView) findViewById(lstResultadoBusca);
        btBuscar = (Button) findViewById(R.id.btBuscar);

        Bundle b = getIntent().getExtras();
        opcaoDados = b.getInt("opcao_dados");

        spnOpcoes.setOnClickListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position > 0)
                    layoutCampoBusca.setVisibility(View.VISIBLE);
                else{
                    layoutCampoBusca.setVisibility(View.INVISIBLE);
                    BuscarTudo();
                }
                opcaoBusca = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String busca = edPalavraChave.getText().toString();

                switch (opcaoBusca){

                    case 1: BuscaPorNome(busca); break;
                    case 2: BuscaPorFabricante(busca); break;
                    case 3: BuscaPorReferencia(busca); break;
                }
            }
        });

        lstResultaBusca.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent telaDados = null;

                switch (opcaoDados){

                    case 1:
                        telaDados = new Intent(BuscaFerramentaActivity.this, AlterarDadosActivity.class);
                        break;

                    case 2:
                        telaDados = new Intent(BuscaFerramentaActivity.this, ConsultarFerramentaActivity.class);
                        break;

                    case 3:
                        telaDados = new Intent(BuscaFerramentaActivity.this, ExcluirFerramentaActivity.class);
                        break;
                }

                c.moveToPosition(position);
                telaDados.putExtra("numreg", c.getInt(0));

                BuscaFerramentaActivity.this.startActivity(telaDados);
                BuscaFerramentaActivity.this.finish();

            }
        });

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
            BuscarTudo();
        }
        catch (Exception e){
            MOstraMensagem("Erro "+ e.toString());
        }
    }

   public void BuscarTudo(){
       c = db.query("ferramentas", new String[]{
               "numreg", "nome_ferramenta", "fabricante", "referencia"} null, null, null, null, null);

       c.moveToFirst();

       ArrayList<DadosFerramenta> dadosFerramentaArray = new ArrayList<DadosFerramenta>();

       for (int x = 0; x < c.getCount(); x++){

           DadosFerramenta dadosFerramenta = new DadosFerramenta();
           dadosFerramenta.setNomeFerramenta(c.getString(1));
           dadosFerramenta.setFabricante(c.getString(2));
           dadosFerramenta.setReferencia(c.getString(3));
           dadosFerramentaArray.add(dadosFerramenta);
           c.moveToNext();
       }

       lstResultaBusca.setAdapter(new MyCustomRowBaseAdapter(this, dadosFerramentaArray));
   }

    public void BuscaPorNome (String palavraChave){

        try

    }
}
