package br.g4.senairunner.senairunner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        numero = getIntent().getStringExtra("numero");

        criaCorridaFragment(savedInstanceState);
    }

    private void criaCorridaFragment(Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragment = new Fragment();
            ft.add(R.id.fragment_main, fragment, "FragmentoCorridas");
            ft.commit();
        }
    }

    public String getNumero() {
        return numero;
    }
}
