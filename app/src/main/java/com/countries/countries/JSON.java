package com.countries.countries;



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
            String jsonText2 = "{"+"\"countries\":"+ jsonText + "}";
            JSONObject json = new JSONObject(jsonText2);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONArray getJSONArray(JSONObject json) throws JSONException {

        JSONArray jsonArray = json.getJSONArray("");
        return jsonArray;
    }

    public static ArrayList<Countries> getList(JSONArray jsonArray) throws JSONException {
        ArrayList<Countries> countrieList = new ArrayList<Countries>();
        // Extract data from json and store into ArrayList as class objects
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json_data = jsonArray.getJSONObject(i);
            Countries countries = new Countries(
                    json_data.getString("country"),
                    json_data.getString("capital"),
                    json_data.getString("region"),
                    json_data.getInt("population")
            );
            countrieList.add(countries);
        }
        
        return countrieList;
    }

    public static ArrayList<Countries> getCountryListByCountry(ArrayList<Countries> countriesList, String country) {
        ArrayList<Countries> countriesListByCountry = new ArrayList<Countries>();
        for (Countries countries : countriesList) {
            if (countries.getCountry().contains(country)) {
                countriesListByCountry.add(countries);
            }
        }
        return countriesListByCountry;
    }

}
