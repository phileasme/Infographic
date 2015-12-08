package com.phileas.infographic.controller;

import android.util.Log;

import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by elizabetamukanova on 07/12/2015.
 */
public class PieChartData {

    private String value1;
    private String value2;
    private float theValue1;
    private float theValue2;

    Country country1;
    Country country2;
    Countries countries;
    String indicator;
    int year;
    float[] values = new float[2];

    public PieChartData(Country country1, Country country2,int year, String indicator) {

        this.country1 = country1;
        this.country2 = country2;
        this.indicator = indicator;
        this.year=year;
    }

    public float[] setData() throws NumberFormatException, NullPointerException {
        try{

            value1 = country1.getIndicator(year, indicator);
            value2 = country2.getIndicator(year, indicator);

            if(value1.equals("null") && value2.equals("null")){
                value1=value2=50+"";
                parseValues();
            }

            else if (value1.equals("null")){
                    value1=0+"";
                parseValues();
                }
                else if (value2.equals("null")) {
                    value2=0+"";
                  parseValues();
                }
            else
                {
                    parseValues();
                }

        } catch (NullPointerException e){
            Log.i("Exception " + e.getMessage(), e + "");
        }

    return values;
    }


    private void parseValues(){
        theValue1 = Float.parseFloat(value1);
        theValue2 = Float.parseFloat(value2);
        values[0]=theValue1;
        values[1]=theValue2;
    }



}
