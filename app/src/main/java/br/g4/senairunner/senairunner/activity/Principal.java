package br.g4.senairunner.senairunner.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.g4.senairunner.senairunner.MainActivity;
import br.g4.senairunner.senairunner.R;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public void onClickProcurar(View v){
        TextView texto = (TextView) findViewById(R.id.edtinscricao);
        if (texto.length() > 0){
            String numero = texto.getText().toString();
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("numero", numero);
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(Principal.this, "Número da inscrição não preenchido", Toast.LENGTH_LONG).show();
            texto.requestFocus();
        }
    }

}
