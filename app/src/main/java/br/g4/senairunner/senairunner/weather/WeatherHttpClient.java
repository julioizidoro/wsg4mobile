package br.g4.senairunner.senairunner.weather;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String API_KEY = "f666ea2d0f16f5c9bc92965a5cce9789";

    public String getWeatherData(String location) throws IOException {
        HttpURLConnection con = null ;
        InputStream is = null;
        String weatherJSON = null;

        try {
            URL url = new URL(BASE_URL + location + "&units=metric&lang=pt&APPID=" + API_KEY);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder buffer = new StringBuilder();
            while ((line = br.readLine()) != null ) {
                buffer.append(line);
                buffer.append("\r\n");
            }
            weatherJSON = buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (is != null)
                is.close();
            if (con != null)
                con.disconnect();
        }
        return weatherJSON;
    }

    public byte[] getImage(String code) throws IOException {
        HttpURLConnection con = null ;
        InputStream is = null;
        byte[] image = null;
        try {
            con = (HttpURLConnection) ( new URL(IMG_URL + code + ".png")).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);

            image = baos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (is != null)
                is.close();
            if (con != null)
                con.disconnect();
        }
        return image;
    }
}
