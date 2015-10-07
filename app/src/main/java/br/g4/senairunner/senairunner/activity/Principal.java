package br.g4.senairunner.senairunner.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.g4.senairunner.senairunner.MainActivity;
import br.g4.senairunner.senairunner.R;

public class Principal extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void onClickProcurar(View v){
        TextView texto = (TextView) findViewById(R.id.edtinscricao);
        if (texto.length() > 0){
            String numero = texto.getText().toString();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("numero", numero);
            startActivity(intent);
        } else {
            Toast.makeText(Principal.this, "Número da inscrição não preenchido", Toast.LENGTH_LONG).show();
            texto.requestFocus();
        }
    }

}
