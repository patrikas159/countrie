package com.corona.coronazp202;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JSON {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

   

    public static ArrayList<Countries> getList(JSONArray jsonArray) throws JSONException {
        ArrayList<Countries> covidList = new ArrayList<Countries>();
        // Extract data from json and store into ArrayList as class objects
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json_data = jsonArray.getJSONObject(i);
            Countries corona = new Countries(
                    json_data.getString("name"),
                    json_data.getString("capital"),
                    json_data.getString("region"),
                    json_data.getString("population"),
                    json_data.getString("area")
            );
            covidList.add(corona);
        }
        return covidList;
    }

    public static ArrayList<Countries> getCountriesListByQuery(ArrayList<Countries> countriesList, String countrieName) {
        ArrayList<Countries> countriesListByCountry = new ArrayList<Countries>();
        for (Countries countries : countriesList) {
            if (countries.getName().contains(countrieName)) {
                countriesListByCountry.add(countries);
            }
        }
        return countriesListByCountry;
    }

}
