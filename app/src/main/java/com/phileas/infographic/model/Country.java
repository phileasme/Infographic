package com.phileas.infographic.model;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Peace on 26/11/2015.
 */
public class Country {
    /** Name of the country and it's id **/
    private String name;


    /**
     * Map of unique indicators
                        (Year/Indicator),Value */
    private HashMap<Pair<Integer,String>,String> indicators;

    /**
     * Country constructor
     * @param name
     */
    public Country(String name){
        this.name = name;
    }

    /** Adds an Indicator or replaces the value of a previous one
     *
     * @param indicator
     * @param year
     * @param value
     */
    public void addIndicator(String indicator,int year,String value){
        Pair<Integer,String> primaryTuple = new Pair<>(year,indicator);
       indicators.put(primaryTuple,value);
    }

    /**
     * Removes all the elements of a certain date
     * @param year
     */
    public void removeYear(int year){
        for(Map.Entry<Pair<Integer,String>,String> entry : indicators.entrySet()){
            Pair<Integer,String> pair = entry.getKey();
            if((pair.first) == year){
                indicators.remove(pair);
            }
        }
    }

    /**
     * Retrieve the list of indicators
     * @return  indicators
     */
    public  HashMap<Pair<Integer,String>,String> getIndicators(){
        return indicators;
    }


    /**
     * Retrieve the name of the country
     * @return
     */
    public String getName(){
        return name;
    }

}
