package br.g4.senairunner.senairunner.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
    private static final String BASE_URL = "http://107.191.109.96/SenaiRunner/runners/";
    private static final String BASE_ULR_CORRIDA =  "http://107.191.109.96/SenaiRunner/runs/";



    public static List<Corrida> getCorridas(String numero) throws JSONException, IOException {

        String json="{'inscricao':[{'corredor':'1','corrida':'2','statusPag':'1'},{'corredor':'1','corrida':'3','statusPag':'1'},{'corredor':'1','corrida':'5','statusPag':'1'}]}";
        // = "{'corridas':[{'idcorrida':'15','nome':'Travessia Torres-TramandaI','descricao':'Travessia Torres-TramandaI','data':'2015-01-31','cidade':'TORRES','estado':'RS','valorinscricao':'45','status':'Confirmada'},{'idcorrida':'16','nome':'Night Run CostAo do Santinho','descricao':'Night Run CostAo do Santinho','data':'2015-01-31','cidade':'FLORIANOPOLIS','estado':'SC','valorinscricao':'48','status':'Confirmada'},{'idcorrida':'17','nome':'Eclipse Run','descricao':'Eclipse Run','data':'2015-01-31','cidade':'RECIFE','estado':'PE','valorinscricao':'51','status':'Confirmada'}]}";

        final List<Corrida> corridas = new ArrayList<Corrida>();

        json = buscarJSon(BASE_URL + numero + "/runs");
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("inscricao");
        for (int i = 0; i < jsonArray.length() ; i++) {
            JSONObject jsonInscricao= jsonArray.getJSONObject(i);
            corridas.add(parserJSONInscricao(jsonInscricao));
        }
        return corridas;
    }

    private static String buscarJSon(String url){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpPost = new HttpGet(url);
        String json=null;
        try{
            httpPost.getMethod();
            HttpResponse resposta = httpClient.execute(httpPost);
            json   = EntityUtils.toString(resposta.getEntity());
        }
        catch(NullPointerException e){ e.printStackTrace(); }
        catch(ClientProtocolException e){ e.printStackTrace(); }
        catch(IOException e){ e.printStackTrace(); }
        return json;
    }

    private static Corrida parserJSONInscricao(JSONObject jsonInscricao) throws IOException, JSONException {
        String id_corrida = jsonInscricao.optString("corrida");
        String  jsonRetorno= buscarJSon(BASE_ULR_CORRIDA + id_corrida);
        JSONObject jsonCorrida= new JSONObject(jsonRetorno);
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
        c.setIdCorrida(jsonCorrida.optLong("id"));
        c.setValorInscricao(jsonCorrida.optDouble("vlrInscricao"));
        c.setStatusCorrida(jsonCorrida.optString("status"));
        return c;
    }
}
