package openweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ParsearJSON {
	

	public String getJson(String provincia) {
		String json = "";
		URL url;

		try {
			url = new URL(" http://api.openweathermap.org/data/2.5/weather?q=" + provincia
					+ "&appid=57703a7a9ab7b873a99116a3ea379748");
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String linea;
			while ((linea = rd.readLine()) != null) {
				json += linea;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return json;
	}

	public void getDatos(String json) {
		Double temperatura = (double) 0, min = (double) 0, max= (double) 0;
		Long sunset= (long) 0, sunrise= (long) 0;
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject objetoPrincipal = (JSONObject) parser.parse(json);
			JSONObject minMax = (JSONObject) objetoPrincipal.get("main");
			JSONObject sunriseSet = (JSONObject) objetoPrincipal.get("sys");
			
			temperatura = (Double) minMax.get("temp");
			min = (Double) minMax.get("temp_min");
			max = (Double) minMax.get("temp_max");
			sunset = (Long) sunriseSet.get("sunset");
			sunrise = (Long) sunriseSet.get("sunrise");
			
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		temperatura = temperatura - 273.15;
		min = min - 273.15;
		max = max - 273.15;
		
		LocalTime sunriseConverted = Instant.ofEpochSecond(sunrise).atOffset(ZoneOffset.UTC).toLocalTime();
		LocalTime sunsetConverted = Instant.ofEpochSecond(sunset).atOffset(ZoneOffset.UTC).toLocalTime();
		
		System.out.println("Temperatura: "+ temperatura +"ºC\nTemperatura mínima: " + min + " ºC\nTemperatura máxima: " + max + " ºC\nSunrise: " + sunriseConverted + "\nSunset: " + sunsetConverted);
	}
		
	
}
