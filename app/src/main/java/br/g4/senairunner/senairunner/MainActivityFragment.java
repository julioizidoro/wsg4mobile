package br.g4.senairunner.senairunner;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.g4.senairunner.senairunner.activity.CorridaActivity;
import br.g4.senairunner.senairunner.adapter.CorridaAdapter;
import br.g4.senairunner.senairunner.dominio.Corrida;

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

        Bundle envelope = getActivity().getIntent().getBundleExtra("listCorridas");
        corridas = (ArrayList<Corrida>)envelope.getSerializable("corridas");

        recyclerView.setAdapter(new CorridaAdapter(getActivity(), corridas, onClickCorrida()));
    }

    private CorridaAdapter.CorridaOnClickListener onClickCorrida(){
        return new CorridaAdapter.CorridaOnClickListener(){
            @Override
            public void onClickCorrida(View view, int index){
                Corrida corrida = corridas.get(index);
                Intent intent = new Intent(getActivity(), CorridaActivity.class);
                intent.putExtra("corrida", corrida);
                startActivity(intent);
            }
        };
    }
}
