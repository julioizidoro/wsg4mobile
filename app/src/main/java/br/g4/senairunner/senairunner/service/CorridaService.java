package br.g4.senairunner.senairunner.service;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

    public static List<Corrida> getCorridas(){
        try {
            String json = readWS();
            List<Corrida> corridas = parserJSON(json);
            return corridas;
        } catch (Exception e){
            Log.e(TAG, "Erro ao ler as corridas: " + e.getMessage(), e);
            return null;
        }
    }

    private static String readWS(){
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(BASE_URL)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;
    }

    private static List<Corrida> parserJSON(String json) throws IOException {
        List<Corrida> corridas = new ArrayList<Corrida>();
        try{
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("corridas");
            JSONArray jsonCorridas = obj.getJSONArray("corrida");
            for (int i=0; i<jsonCorridas.length();i++){
                JSONObject jsonCorrida = jsonCorridas.getJSONObject(i);
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
                corridas.add(c);
            }
        } catch (JSONException je){
            throw new IOException(je.getMessage(), je);
        }
        return corridas;
    }

}
