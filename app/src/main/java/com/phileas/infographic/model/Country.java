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
    private String id;


    /**
     * Map of unique indicators
     *                   (Year/Indicator) ,Value
     */
    private HashMap<Pair<Integer,String>,Double> indicators;

    /**
     * Country constructor
     * @param name
     * @param id
     */
    public Country(String name,String id){
        this.name = name;
        this.id = id;
    }

    /** Adds an Indicator or replaces the value of a previous one
     *
     * @param indicator
     * @param year
     * @param value
     */
    public void addIndicator(String indicator,int year, double value){
        Pair<Integer,String> primaryTuple = new Pair<>(year,indicator);
       indicators.put(primaryTuple,value);
    }

    /** Removes all the elements of a certain date
     *
     * @param year
     */
    public void removeYear(int year){
        for(Map.Entry<Pair<Integer,String>,Double> entry : indicators.entrySet()){
            Pair<Integer,String> pair = entry.getKey();
            if((pair.first) == year){
                indicators.remove(pair);
            }
        }
    }

    /**
     *  Retrieve the list of indicators
     * @return  indicators
     */
    public  HashMap<Pair<Integer,String>,Double> getIndicators(){
        return indicators;
    }


    /**
     * Retrieve the name of the country
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Retrieve the Id of the country
     * @return
     */
    public String getId(){
        return id;
    }
}
