package br.g4.senairunner.senairunner.service;

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

public class CorridaService {

    private static final boolean LOG_ON = false;
    private static final String TAG = "CorridaService";
    private static final String BASE_URL = "http://www.ceolato.com.br/wsg4server/corredores/";
    private static final String BASE_ULR_CORRIDA =  "http://www.ceolato.com.br/wsg4server/corridas/";

    public static List<Corrida> getCorridas(String numero) throws JSONException, IOException {
        final List<Corrida> corridas = new ArrayList<Corrida>();

        String json = buscarJSon(BASE_URL + numero + "/corridas");

        JSONObject jsonObject = new JSONObject(json);

        JSONArray jsonArray = jsonObject.getJSONArray("corridas");
        for (int i = 0; i < jsonArray.length() ; i++) {
            JSONObject jsonInscricao = jsonArray.getJSONObject(i);
            corridas.add(parserJSONInscricao(jsonInscricao));
        }
        return corridas;
    }

    private static String buscarJSon(String url){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpPost = new HttpGet(url);
        String json = null;
        try{
            httpPost.getMethod();
            HttpResponse resposta = httpClient.execute(httpPost);
            json = EntityUtils.toString(resposta.getEntity());
        }
        catch(NullPointerException e){ e.printStackTrace(); }
        catch(ClientProtocolException e){ e.printStackTrace(); }
        catch(IOException e){ e.printStackTrace(); }
        return json;
    }

    private static Corrida parserJSONInscricao(JSONObject jsonInscricao) throws IOException, JSONException {
        String id_corrida = jsonInscricao.optString("idcorrida");

        String jsonRetorno = buscarJSon(BASE_ULR_CORRIDA + id_corrida);

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

        c.setIdCorrida(jsonCorrida.optLong("idcorrida"));
        c.setValorInscricao(jsonCorrida.optDouble("valorinscricao"));
        c.setStatusCorrida(jsonCorrida.optString("status"));
        return c;
    }
}
