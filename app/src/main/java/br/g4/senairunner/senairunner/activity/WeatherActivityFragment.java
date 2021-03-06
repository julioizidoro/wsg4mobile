package br.g4.senairunner.senairunner.activity;

import org.json.JSONException;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.g4.senairunner.senairunner.R;
import br.g4.senairunner.senairunner.dominio.Corrida;
import br.g4.senairunner.senairunner.weather.JSONWeatherParser;
import br.g4.senairunner.senairunner.weather.WeatherHttpClient;
import br.g4.senairunner.senairunner.weather.dominio.Weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by CarlosAlberto on 02/07/2015.
 */
public class WeatherActivityFragment extends android.support.v4.app.Fragment {

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView hum;
    private ImageView imgView;
    private Corrida corrida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        cityText = (TextView) view.findViewById(R.id.cityText);
        condDescr = (TextView) view.findViewById(R.id.condDescr);
        temp = (TextView) view.findViewById(R.id.temp);
        hum = (TextView) view.findViewById(R.id.hum);
        press = (TextView) view.findViewById(R.id.press);
        windSpeed = (TextView) view.findViewById(R.id.windSpeed);
        windDeg = (TextView) view.findViewById(R.id.windDeg);
        imgView = (ImageView) view.findViewById(R.id.condIcon);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Intent i = getActivity().getIntent();
        corrida = (Corrida) i.getSerializableExtra("corrida");
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{corrida.getCidade() + "/" + corrida.getEstado()});
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));
            try {
                weather = JSONWeatherParser.getWeather(data);
                weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

            cityText.setText(weather.location.getCity() + ", " + weather.location.getCountry());
            condDescr.setText("Condição do tempo: " + "(" + weather.currentCondition.getDescr() + ")");
            temp.setText("" + Math.round(weather.temperature.getTemp()) + "ºC");
            hum.setText("" + weather.currentCondition.getHumidity() + "%");
            press.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + weather.wind.getSpeed() + " m/s");
            windDeg.setText("" + weather.wind.getDeg() + "º");
        }

    }
}
