package br.g4.senairunner.senairunner.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.text.SimpleDateFormat;

import br.g4.senairunner.senairunner.R;
import br.g4.senairunner.senairunner.dominio.Corrida;

public class CorridaAdapter extends RecyclerView.Adapter<CorridaAdapter.CorridasViewHolder> {

    protected static final String TAG = "senairunner";
    private final List<Corrida> corridas;
    private final Context context;
    private CorridaOnClickListener corridaOnClickListener;

    public CorridaAdapter(Context context, List<Corrida> corridas, CorridaOnClickListener corridaOnClickListener){
        this.context = context;
        this.corridas = corridas;
        this.corridaOnClickListener = corridaOnClickListener;
    }

    @Override
    public int getItemCount(){
        return this.corridas != null ? this.corridas.size() : 0;
    }

    @Override
    public CorridasViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_corrida, viewGroup, false);
        return new CorridasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CorridasViewHolder holder, final int position){
        Corrida c = corridas.get(position);
        holder.data.setText(new SimpleDateFormat("dd").format(c.getDataCorrida()));
        holder.mes.setText(new SimpleDateFormat("MMM").format(c.getDataCorrida()));
        holder.firstLine.setText(c.getNomeCorrida());
        holder.secondLine.setText(c.getCidade() + "/" + c.getEstado());

        if (corridaOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v){
                   corridaOnClickListener.onClickCorrida(holder.itemView, position);
               }
            });
        }
    }

    public interface CorridaOnClickListener{
        public void onClickCorrida(View view, int idx);
    }

    public static class CorridasViewHolder extends RecyclerView.ViewHolder {
        public TextView firstLine;
        public TextView secondLine;
        public TextView data;
        public TextView mes;
        CardView cardView;

        public CorridasViewHolder(View view){
            super(view);
            data = (TextView) view.findViewById(R.id.data);
            mes = (TextView) view.findViewById(R.id.mes);
            firstLine = (TextView) view.findViewById(R.id.firstLine);
            secondLine = (TextView) view.findViewById(R.id.secondLine);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}

