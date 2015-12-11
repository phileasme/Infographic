package com.phileas.infographic.controller;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 *Class that has for use to read all the json files.
 *@Author : Phileas Hocquard
 */
public class ReadAllAssets {

    /**
     * Method to retrieve all the indicators from all the json files located in the assets folder.
     * @param path
     * @param myContext
     * @return Collection of countries defined by a countries class.
     */
    public Countries ReadAllAssetFiles(String path,Context myContext) {
        Countries countries = new Countries();
        HashMap<String,HashMap<Pair<Integer,String>,String>> countriesMap;
        String [] list;
        try {
            countriesMap  = new HashMap();
            list = myContext.getAssets().list(path);
            if (list.length > 0) {
                for (String file : list) {
                    int i = file.lastIndexOf('.');
                    String extension = "";
                    if (i > 0) {
                        extension = file.substring(i+1);
                    }
                    if (ReadAllAssetFiles((path + "/" + file), myContext) == null) {
                        return null;
                    } else {
                        if(extension.equals("json")) {
                            ReadFromJson readCurrentFile = new ReadFromJson(file, myContext);
                            HashMap<String,HashMap<Pair<Integer,String>,String>> countryPair_DateIndicatorValue = readCurrentFile.loadJSONFromAsset();
                            for(String a : countryPair_DateIndicatorValue.keySet()){
                                if(!countriesMap.containsKey(a)){
                                    countriesMap.putAll(countryPair_DateIndicatorValue);
                                }else{
                                    for(Pair<Integer,String> b : countryPair_DateIndicatorValue.get(a).keySet()) {
                                        countriesMap.get(a).putAll(countryPair_DateIndicatorValue.get(a));
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } catch (IOException e) {
            return null;
        }

        for (String a : countriesMap.keySet()) {
            Country country = new Country(a);
            country.addMultipleIndicators(countriesMap.get(a));
            countries.add(country);
        }

        //If we return 7*2*248 values by default then we have the right amount of values.
        int totalNumberOfCountriesIndicators = 0;
        for (Country z : countries.getCountries()) {
            for (Pair<Integer, String> d : z.getIndicators().keySet()) {
                totalNumberOfCountriesIndicators++;
            }
        }
        if(totalNumberOfCountriesIndicators == 7*2*248){
            countries.HasDoneFirst();
        }


        return countries;
    }

}