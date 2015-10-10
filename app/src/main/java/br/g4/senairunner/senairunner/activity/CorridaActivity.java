package br.g4.senairunner.senairunner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import br.g4.senairunner.senairunner.R;
import br.g4.senairunner.senairunner.dominio.Corrida;

public class CorridaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrida);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Corrida corrida = (Corrida) getIntent().getSerializableExtra("corrida");
        preencheCampos(corrida);
    }

    private void preencheCampos(Corrida corrida) {
        TextView txCorrida = (TextView) findViewById(R.id.txCorrida);
        txCorrida.setText(corrida.getNomeCorrida());

        TextView txData = (TextView) findViewById(R.id.txData);
        txData.setText(new SimpleDateFormat("(dd/MM/yyyy)").format(corrida.getDataCorrida()));

        TextView txCidade = (TextView) findViewById(R.id.txCidade);
        txCidade.setText(corrida.getCidade()+"/"+corrida.getEstado());

        TextView txStatus = (TextView) findViewById(R.id.txStatus);
        txStatus.setText(corrida.getStatusCorrida());

        TextView txValorInscricao = (TextView) findViewById(R.id.txValor);
        txValorInscricao.setText(String.format("R$ %.2f", corrida.getValorInscricao()));
    }
}
