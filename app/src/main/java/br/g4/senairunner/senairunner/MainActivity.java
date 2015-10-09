package br.g4.senairunner.senairunner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.g4.senairunner.senairunner.activity.CorridaActivity;
import br.g4.senairunner.senairunner.adapter.CorridaAdapter;
import br.g4.senairunner.senairunner.dominio.Corrida;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    private List<Corrida> corridas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Bundle envelope = getIntent().getBundleExtra("listCorridas");
        corridas = (ArrayList<Corrida>)envelope.getSerializable("corridas");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new CorridaAdapter(this, corridas, onClickCorrida()));
    }

    private CorridaAdapter.CorridaOnClickListener onClickCorrida(){
        return new CorridaAdapter.CorridaOnClickListener(){
            @Override
            public void onClickCorrida(View view, int index){
                Corrida corrida = corridas.get(index);
                Intent intent = new Intent(MainActivity.this, CorridaActivity.class);
                intent.putExtra("corrida", corrida);
                startActivity(intent);
            }
        };
    }
}
