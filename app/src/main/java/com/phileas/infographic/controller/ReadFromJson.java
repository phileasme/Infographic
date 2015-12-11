package com.phileas.infographic.controller;

import android.content.Context;
import android.os.Message;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Local json file reader
 *@Author : Phileas Hocquard
 */
public class ReadFromJson {

    String filename;
    Context currentContext;

    //COUNTRY             YEAR Indicator   VALUE
    HashMap<String,HashMap<Pair<Integer,String>,String>> countrySpecificIndex;

    /**
     *
     * @param file
     * @param context
     */
    public ReadFromJson(String file,Context context){
        currentContext = context;
        filename =file;
        countrySpecificIndex = new HashMap();
    }

    /**
     *Method that matches the required information from a json file to a country.
     * @return Map of a specific indicator for multiple countries.
     */
    public  HashMap<String,HashMap<Pair<Integer,String>,String>>  loadJSONFromAsset() {

        InputStream is = null;
        String jsonData = "";
        BufferedReader br = null;
        try {

            String bufferString = "";
            is = currentContext.getAssets().open(filename);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int bufferLength;
            while ((bufferString = br.readLine()) != null) {
                jsonData += bufferString + "\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        extractingJsonInfo(jsonData);
        return countrySpecificIndex;
    }

    /**
     *Method that extract the Json from the files
     * @param jsonData
     * @return Hashmap relation of a country's with their respected indicators
     */
    public HashMap<String,HashMap<Pair<Integer,String>,String>> extractingJsonInfo(String jsonData){
        JSONArray result = null;
        try {
            result = new JSONArray(jsonData);

            JSONArray countryList = result.getJSONArray(1);

            JSONArray currentCountryInfo;
            for (int i = 0; i < countryList.length(); ++i) {
                JSONObject country = countryList.getJSONObject(i);
                JSONObject countryField = country.getJSONObject("country");
                JSONObject indicatorField = country.getJSONObject("indicator");

                // Gets the country's name,indicator,value and the year of the indicator's value.
                String name = countryField.getString("value");
                String indicator = indicatorField.getString("id");
                String value = country.getString("value");
                Integer year = Integer.parseInt(country.getString("date"));

                Pair<Integer, String> specificIndicator = new Pair(year, indicator);

                if (countrySpecificIndex.containsKey(name)) {
                    countrySpecificIndex.get(name).put(specificIndicator, value);
                } else {
                    HashMap<Pair<Integer, String>, String> map = new HashMap();
                    map.put(specificIndicator, value);
                    countrySpecificIndex.put(name, map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countrySpecificIndex;
    }

}