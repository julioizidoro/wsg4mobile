package br.g4.senairunner.senairunner.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.g4.senairunner.senairunner.MainActivity;
import br.g4.senairunner.senairunner.R;
import br.g4.senairunner.senairunner.dominio.Corrida;
import br.g4.senairunner.senairunner.service.CorridaService;

public class Principal extends AppCompatActivity {

    List<Corrida> corridas;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        texto = (TextView) findViewById(R.id.edtinscricao);
    }

    public void onClickProcurar(View v){
        if (texto.length() > 0){
            String numero = texto.getText().toString();
            obtemCorridas(numero);
        } else
            alertValidacao("Número da inscrição não preenchido");
    }

    private void obtemCorridas(final String numero) {
        final ProgressDialog progressDlg = ProgressDialog.show(
                Principal.this,
                getString(R.string.app_name),
                "Obtendo registros...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    corridas = CorridaService.getCorridas(numero);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDlg.dismiss();
                            if (corridas.size() == 0)
                                alertValidacao("Esse corredor não está cadastrado em nenhuma corrida");
                            else
                                redirecionaParaListaCorridas(numero);
                        }
                    });
                } catch (Exception e){
                    corridas = null;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDlg.dismiss();
                            alertValidacao("Não existe nenhum corredor com esse número");
                        }
                    });
                }
            }
        }).start();
    }

    private void redirecionaParaListaCorridas(String numero) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("numero", numero);
        editor.commit();

        Intent intent = new Intent(Principal.this, MainActivity.class);
        startActivity(intent);
    }

    private void alertValidacao(String mensagem) {
        Toast.makeText(Principal.this, mensagem, Toast.LENGTH_LONG).show();
        texto.requestFocus();
    }

}
