package com.phileas.infographic.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;
import com.phileas.infographic.view.MainActivity;

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
    private boolean value;
    private String countryNullName;
    private String countryNotNull;

    Country country1;
    Country country2;
    Countries countries;
    String indicator;
    int year;
    float[] values = new float[2];
    MainActivity mainActivity = new MainActivity();
    private Context context = mainActivity.getBaseContext();


    public PieChartData(Country country1, Country country2, int year, String indicator) {

        this.country1 = country1;
        this.country2 = country2;
        this.indicator = indicator;
        this.year = year;
    }


    public float[] setData() throws NumberFormatException, NullPointerException {
        try {
            value1 = country1.getIndicator(year, indicator);
            value2 = country2.getIndicator(year, indicator);


            if (value1.equals("null") && value2.equals("null")) {
                value1 = value2 = 0 + "";
                parseValues();
            } else if (value1.equals("null")) {
                value1 = 0 + "";
                parseValues();
            } else if (value2.equals("null")) {
                value2 = 0 + "";
                parseValues();
            } else {
                parseValues();
            }

        } catch (NullPointerException e) {
            Log.i("Exception " + e.getMessage(), e + "");
        }

        return values;
    }


    private void parseValues() {
        theValue1 = Float.parseFloat(value1);
        theValue2 = Float.parseFloat(value2);
        values[0] = theValue1;
        values[1] = theValue2;
    }

    public boolean getNullValues() {

        if (values[0] == 0 && values[1] == 0) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean getNullValue(){

        if (values[0]==0 || values[1]==0){
            value=true;
        }
        return value;
    }

    public String getCountryThatIsNull(){
        if(values[0]==0){
            countryNullName=country1.getName();
            countryNotNull=country2.getName() + " has " + values[1] + " exports of goods and services (% of GDP).";
        }
        else{
            countryNullName=country2.getName();
            countryNotNull=country1.getName()+ " has " + values[0] + " exports of goods and services (% of GDP).";;
        }
        return countryNullName + ". " + countryNotNull;
    }

}
