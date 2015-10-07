package br.g4.senairunner.senairunner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.g4.senairunner.senairunner.activity.CorridaActivity;
import br.g4.senairunner.senairunner.adapter.CorridaAdapter;
import br.g4.senairunner.senairunner.dominio.Corrida;
import br.g4.senairunner.senairunner.service.CorridaService;

public class MainActivityFragment extends android.support.v4.app.Fragment {

    protected RecyclerView recyclerView;
    private List<Corrida> corridas;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        taskCorridas();
    }

    private void taskCorridas(){
       new GetCorridasTask().execute();
    }

    private CorridaAdapter.CorridaOnClickListener onClickCorrida(){
        return new CorridaAdapter.CorridaOnClickListener(){
            @Override
            public void onClickCorrida(View view, int idx){
                Corrida c = corridas.get(idx);
                Intent intent = new Intent(getActivity(), CorridaActivity.class);
                intent.putExtra("corrida", c);
                startActivity(intent);
                Toast.makeText(getActivity(), "Corrida: " + c.getNomeCorrida(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private class GetCorridasTask extends AsyncTask<Void, Void, List<Corrida>>{
        @Override
        protected List<Corrida> doInBackground(Void... params){
            try{
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                return CorridaService.getCorridas(settings.getString("numero","0"));
            }catch (Exception e){
                Log.e("MainFragment", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(List<Corrida> corridas){
            if (corridas != null){
                MainActivityFragment.this.corridas = corridas;
                recyclerView.setAdapter(new CorridaAdapter(getActivity(), corridas, onClickCorrida()));
            }
        }
    }
}
