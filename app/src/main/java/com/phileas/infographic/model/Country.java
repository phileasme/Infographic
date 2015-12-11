package com.phileas.infographic.model;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class definition of a Country.
 * @Author: Phileas Hocquard
 */
public class Country {

    /** Name of the country **/
    private String name;
    /**
     * Map of unique indicators
                        (Year/Indicator),Value */
    private HashMap<Pair<Integer,String>,String> indicators;

    private boolean checked;
    /**
     * Country constructor
     * @param name
     */
    public Country(String name){
        this.name = name;
        this.checked=false;
        indicators = new HashMap<>();
    }

    /** Adds an Indicator or replaces the value of a previous one
     *
     * @param value
     */
    public void addIndicator(Integer date,String indicator,String value){
        Pair<Integer,String> primaryTuple = new Pair<Integer,String>(date,indicator);

       indicators.put(primaryTuple,value);
    }

    public void addMultipleIndicators(HashMap<Pair<Integer,String>,String> val){
        indicators.putAll(val);
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
     * Method that retrieves a specific Indicators string value
     * @param date
     * @param indicatorName
     * @return String value of an Indicator
     */
    public String getIndicator(Integer date,String indicatorName){
        Pair<Integer,String> primaryTuple = new Pair<Integer,String>(date,indicatorName);
        return indicators.get(primaryTuple);
    }

    /**
     * Retrieve the name of the country
     * @return
     */
    public String getName(){return name;}




}
