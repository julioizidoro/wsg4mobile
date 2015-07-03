package br.g4.senairunner.senairunner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import br.g4.senairunner.senairunner.R;
import br.g4.senairunner.senairunner.dominio.Corrida;

/**
 * Created by CarlosAlberto on 02/07/2015.
 */
public class CorridaActivity extends FragmentActivity {

    private TextView txCorrida;
    private TextView txData;
    private TextView txCidade;
    private TextView txEstado;
    private TextView txValorInscricao;
    private TextView txStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrida);
        bindViews();
        Intent i = getIntent();
        Corrida corrida = (Corrida) i.getSerializableExtra("corrida");
        preencheCampo(corrida);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = new Fragment();
            ft.add(R.id.fragment_weather, fragment, "FragmentoWeather");
            ft.commit();
        }
    }

    private void bindViews(){
        txCorrida = (TextView) findViewById(R.id.txCorrida);
        txData = (TextView) findViewById(R.id.txData);
        txCidade = (TextView) findViewById(R.id.txCidade);
        txEstado = (TextView) findViewById(R.id.txEstado);
        txStatus = (TextView) findViewById(R.id.txStatus);
        txValorInscricao = (TextView) findViewById(R.id.txValor);
    }

    private void preencheCampo(Corrida corrida){
        txCorrida.setText(corrida.getNomeCorrida());
        txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(corrida.getDataCorrida()));
        txCidade.setText(corrida.getCidade());
        txEstado.setText(corrida.getEstado());
        txStatus.setText(corrida.getStatusCorrida());
        txValorInscricao.setText(String.format("%.2f", corrida.getValorInscricao()));
    }
}
