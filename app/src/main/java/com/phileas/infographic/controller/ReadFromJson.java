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
 * Created by Peace on 26/11/2015.
 */

/**
 * Local json file reader
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
            loadJSONFromAsset();
    }

    /**
     *
     * @return
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
        JSONArray result = null;
        try {
                result = new JSONArray(jsonData);

            JSONArray countryList = result.getJSONArray(1);

            JSONArray currentCountryInfo;
            for (int i = 0; i < countryList.length(); ++i) {
                JSONObject country = countryList.getJSONObject(i);
                JSONObject countryField = country.getJSONObject("country");
                JSONObject indicatorField = country.getJSONObject("indicator");

                // Gets the country name
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
        for(String a : countrySpecificIndex.keySet()){
            for(Pair<Integer, String> b :countrySpecificIndex.get(a).keySet()){
                Log.i("country, value",a+" value: "+countrySpecificIndex.get(a).get(b));
            }
        }
    return countrySpecificIndex;
    }
}
