package com.phileas.infographic.model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Peace on 26/11/2015.
 */
public class Country {
    private String name;
    /**
     *
     */
                      //(Year/Indicator) ,Value
    private HashMap<Pair<Integer,String>,Double> indicators;

    public Country(String name){
        this.name = name;
    }

    public void addIndicator(String indicator,int year, double value){
        Pair<Integer,String> primaryTuple = new Pair<>(year,indicator);
       indicators.put(primaryTuple,value);
    }

    /** Removes all the elements of a certain date **/
    public void removeYear(int year){
        for(Map.Entry<Pair<Integer,String>,Double> entry : indicators.entrySet()){
            Pair<Integer,String> pair = entry.getKey();
            if((pair.first) == year){
                indicators.remove(pair);
            }
        }
    }

    public  HashMap<Pair<Integer,String>,Double> getIndicators(){
        return indicators;
    }

}
