package br.g4.senairunner.senairunner.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import br.g4.senairunner.senairunner.dominio.Corrida;

/**
 * Created by 1541714 on 30/06/2015.
 */
public class CorridaService {

    private static final boolean LOG_ON = false;
    private static final String TAG = "CorridaService";
    private static final String BASE_URL = "http://ceolato.com.br/wsg4server/corridas";

    public static List<Corrida> getCorridas() throws JSONException, IOException {

        String json = "{'corridas':[{'idcorrida':'15','nome':'Travessia Torres-TramandaI','descricao':'Travessia Torres-TramandaI','data':'2015-01-31','cidade':'TORRES','estado':'RS','valorinscricao':'45','status':'Confirmada'},{'idcorrida':'16','nome':'Night Run CostAo do Santinho','descricao':'Night Run CostAo do Santinho','data':'2015-01-31','cidade':'FLORIANOPOLIS','estado':'SC','valorinscricao':'48','status':'Confirmada'},{'idcorrida':'17','nome':'Eclipse Run','descricao':'Eclipse Run','data':'2015-01-31','cidade':'RECIFE','estado':'PE','valorinscricao':'51','status':'Confirmada'}]}";

        final List<Corrida> corridas = new ArrayList<Corrida>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("corridas");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonCorrida = jsonArray.getJSONObject(i);
            corridas.add(parserJSON(jsonCorrida));
        }


        //try {
        //     JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(BASE_URL, new Response.Listener<JSONArray>() {
        //         @Override
        //        public void onResponse(JSONArray jsonArray) {
        //            try {
        //               for (int i = 0; i < jsonArray.length(); i++) {
        //                    JSONObject jsonCorrida = jsonArray.getJSONObject(i);
        //                    corridas.add(parserJSON(jsonCorrida));
        //                }
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            }
        //        }
        //    }, new Response.ErrorListener() {
        //        @Override
        //        public void onErrorResponse(VolleyError volleyError) {

        //        }
        //    });

        //} catch (Exception e){
        //    Log.e(TAG, "Erro ao ler as corridas: " + e.getMessage(), e);
        //    return null;
        //}
        return corridas;
    }


    private static Corrida parserJSON(JSONObject jsonCorrida) throws IOException {
        Corrida c = new Corrida();
        c.setNomeCorrida(jsonCorrida.optString("nome"));
        c.setDescricaoCorrida(jsonCorrida.optString("descricao"));
        c.setCidade(jsonCorrida.optString("cidade"));
        c.setEstado(jsonCorrida.optString("estado"));
        GregorianCalendar d = new GregorianCalendar();
        String data = jsonCorrida.optString("data");
        int ano = Integer.parseInt(data.substring(0,4));
        int mes = Integer.parseInt(data.substring(5,7));
        int dia = Integer.parseInt(data.substring(8,10));
        d.set(ano, mes, dia);
        c.setDataCorrida(d.getTime());
        c.setIdCorrida(jsonCorrida.optLong("idcorrida"));
        c.setValorInscricao(jsonCorrida.optDouble("valorinscricao"));
        c.setStatusCorrida(jsonCorrida.optString("status"));
        return c;
    }

}
