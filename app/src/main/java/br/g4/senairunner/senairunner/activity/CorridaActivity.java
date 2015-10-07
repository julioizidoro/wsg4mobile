package br.g4.senairunner.senairunner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import br.g4.senairunner.senairunner.R;
import br.g4.senairunner.senairunner.dominio.Corrida;

public class CorridaActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrida);

        Corrida corrida = (Corrida) getIntent().getSerializableExtra("corrida");
        preencheCampos(corrida);

        criaWeatherFragment(savedInstanceState);
    }

    private void criaWeatherFragment(Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = new Fragment();
            ft.add(R.id.fragment_weather, fragment, "FragmentoWeather");
            ft.commit();
        }
    }

    private void preencheCampos(Corrida corrida) {
        TextView txCorrida = (TextView) findViewById(R.id.txCorrida);
        txCorrida.setText(corrida.getNomeCorrida());

        TextView txData = (TextView) findViewById(R.id.txData);
        txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(corrida.getDataCorrida()));

        TextView txCidade = (TextView) findViewById(R.id.txCidade);
        txCidade.setText(corrida.getCidade());

        TextView txEstado = (TextView) findViewById(R.id.txEstado);
        txEstado.setText(corrida.getEstado());

        TextView txStatus = (TextView) findViewById(R.id.txStatus);
        txStatus.setText(corrida.getStatusCorrida());

        TextView txValorInscricao = (TextView) findViewById(R.id.txValor);
        txValorInscricao.setText(String.format("%.2f", corrida.getValorInscricao()));
    }
}
