package com.phileas.infographic.controller;

import android.text.Html;
import android.widget.TextView;

import com.phileas.infographic.model.Country;

import java.util.ArrayList;

/**
 * Created by Hannah on 08/12/2015.
 */
public class TextBoxController {
    Country one;
    Country two;
    int year;
    TextView textViewPayTax, textViewNewBusinessReg, textViewEaseOfBusiness, textViewExportsCountryOne, textViewExportsCountryTwo;
    String indicatorNewBusinessReg, indicatorEaseOfBusiness, indicatorTimePayTax, indicatorExports;
    ArrayList<TextView> textviews;
    String nameOfCountry, nameOfCountry2;
    String country1BusinessData, country1EaseData, country1TaxData, country2BusinessData, country2EaseData, country2TaxData, country1ExportData, country2ExportData;

    public TextBoxController(ArrayList<TextView> inds, Country a, Country b, int year) {
        one = a;
        two = b;
        this.year = year;
        textviews = inds;
    }


    public void setText() {

        //new business registered
        indicatorNewBusinessReg = "IC.BUS.NREG";
        //ease of doing business
        indicatorEaseOfBusiness = "IC.BUS.EASE.XQ";
        //time to pay taxes
        indicatorTimePayTax = "IC.TAX.DURS";
        //exports of goods and services (% of GDP)
        indicatorExports="NE.EXP.GNFS.ZS";


        //pay tax data
        textViewPayTax = textviews.get(0);
        //new business data
        textViewNewBusinessReg = textviews.get(1);
        //ease of business data
        textViewEaseOfBusiness = textviews.get(2);
        textViewExportsCountryOne = textviews.get(3);
        textViewExportsCountryTwo = textviews.get(4);

        nameOfCountry = one.getName();
        nameOfCountry2 = two.getName();

        createText(one, two);




        //setting tax data
        checkNullDataTax();
        //setting ease of business data
        checkNullDataEase();
        //setting new business data
        checkNullDataBusiness();
        //setting exports data
        checkNullDataExports();


    }

    public void createText(Country any, Country any1) {
        //getting tax data for country1
        country1BusinessData = any.getIndicator(year, indicatorNewBusinessReg);
        //getting ease of business data for country 1
        country1EaseData = any.getIndicator(year, indicatorEaseOfBusiness);
        //getting new business data for country1
        country1TaxData = any.getIndicator(year, indicatorTimePayTax);
        //getting exports of goods data for country2
        country1ExportData = any.getIndicator(year, indicatorExports);




        //getting tax data for country2
        country2BusinessData = any1.getIndicator(year, indicatorNewBusinessReg);
        //getting ease of business data for country 2
        country2EaseData = any1.getIndicator(year, indicatorEaseOfBusiness);
        //getting new business registered for country2
        country2TaxData = any1.getIndicator(year, indicatorTimePayTax);
        //getting exports of goods data for country2
        country2ExportData = any1.getIndicator(year, indicatorExports);



    }

    public void checkNullDataBusiness(){
        if(country1BusinessData.equals("null")&& country2BusinessData.equals("null")){
            textViewNewBusinessReg.setText("No data available for these countries");
        }else if( country1BusinessData.equals("null") && country2BusinessData != null){
            textViewNewBusinessReg.setText(nameOfCountry + " has no data available, and " + nameOfCountry2 + " has " + country2BusinessData + " businesses registered.");
        }else if(country1BusinessData!=null && country2BusinessData.equals("null")){
            textViewNewBusinessReg.setText(nameOfCountry + " has " +  country1BusinessData  + " businesses and " + nameOfCountry2 + " has no data available.");
        }else  {
            textViewNewBusinessReg.setText(nameOfCountry + " has "+ country1BusinessData  +" new businesses registered and " + nameOfCountry2 + " has " + country2BusinessData + ".");

        }

    }

    public void checkNullDataTax(){

        if(country1TaxData.equals("null")&& country2TaxData.equals("null")){
            textViewPayTax.setText("No data available for these countries");
        }else if(country1TaxData.equals("null") && country2TaxData != null){
            textViewPayTax.setText(nameOfCountry + " has no data available, and " + nameOfCountry2 + " takes " + country2TaxData + " hours.");
        }else if(country1TaxData!=null && country2TaxData.equals("null")){
            textViewPayTax.setText(nameOfCountry + " takes " + country1TaxData + " hours and " + nameOfCountry2 + " has no data available.");
        }else {
            textViewPayTax.setText(nameOfCountry + " takes " + country1TaxData + " hours and " + nameOfCountry2 + " takes " + country2TaxData + " hours.");

        }

    }

    public void checkNullDataEase(){

        if(country1EaseData.equals("null")&& country2EaseData.equals("null")){
            textViewEaseOfBusiness.setText("No data available for these countries");
        }else if(country1EaseData.equals("null") && country2EaseData != null){
            textViewEaseOfBusiness.setText(nameOfCountry + " has no data available, and " + nameOfCountry2 + " was ranked at " + country2EaseData + ". (1=most business-friendly regulations)");
        }else if(country1EaseData!=null && country1EaseData.equals("null")){
            textViewEaseOfBusiness.setText(nameOfCountry + " was ranked at " + country1EaseData + " and " + nameOfCountry2 + " has no data available.");
        }else {
            textViewEaseOfBusiness.setText(nameOfCountry + " was ranked " + country1EaseData + " and " + nameOfCountry2 + " was ranked at " + country2EaseData + ". (1=most business-friendly regulations)");

        }

    }

    public void checkNullDataExports(){
        if(country1ExportData.equals("null")){
            textViewExportsCountryOne.setText("Sorry no data is currently available for " + nameOfCountry);
        } else {
            textViewExportsCountryOne.setText(country1ExportData + " %" + "\n " + nameOfCountry);

        }if (country2ExportData.equals("null")){
            textViewExportsCountryTwo.setText("Sorry no data is currently available for "+ nameOfCountry);
        }else {
            textViewExportsCountryTwo.setText(country2ExportData + " %" + "\n " + nameOfCountry2);

        }

    }



}