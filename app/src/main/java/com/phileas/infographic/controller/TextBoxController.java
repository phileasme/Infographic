package com.phileas.infographic.controller;

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
    TextView textViewPayTax, textViewNewBusinessReg, textViewEaseOfBusiness, indicator4, indicator5;
    String indicatorNewBusinessReg, indicatorEaseOfBusiness, indicatorTimePayTax, ind4, ind5;
    ArrayList<TextView> textviews;
    String nameOfCountry, nameOfCountry2;
    String country1BusinessData, country1EaseData, country1TaxData, country2BusinessData, country2EaseData, country2TaxData;
    Double data1double, data2double, data3double, data4double, data5double, data6double, data7double, data8double, data9double, data10double;

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

        //pay tax data
        textViewPayTax = textviews.get(0);
        //new business data
        textViewNewBusinessReg = textviews.get(1);
        //ease of business data
        textViewEaseOfBusiness = textviews.get(2);

        nameOfCountry = one.getName();
        nameOfCountry2 = two.getName();

        createText(one, two);


        //setting tax data
        textViewPayTax.setText(nameOfCountry + " takes " + country1TaxData + " hours and " + nameOfCountry2 + " takes " + country2TaxData + " hours.");
        //setting ease of business data
        textViewEaseOfBusiness.setText(nameOfCountry + " was ranked " + country1EaseData + " and " + nameOfCountry2 + " was ranked " + country2EaseData + ". (1=most business-friendly regulations)");
        //setting new business data
        textViewNewBusinessReg.setText(nameOfCountry + " has " + country1BusinessData + " businesses and " + nameOfCountry2 + " has " + country2BusinessData + ".");



    }

    public void createText(Country any, Country any1) {
        //getting tax data for country1
        country1BusinessData = any.getIndicator(year, indicatorNewBusinessReg);
        //getting ease of business data for country 1
        country1EaseData = any.getIndicator(year, indicatorEaseOfBusiness);
        //getting new business data for country1
        country1TaxData = any.getIndicator(year, indicatorTimePayTax);




        //getting tax data for country2
        country2BusinessData = any1.getIndicator(year, indicatorNewBusinessReg);
        //getting ease of business data for country 2
        country2EaseData = any1.getIndicator(year, indicatorEaseOfBusiness);
        //getting new business registered for country2
        country2TaxData = any1.getIndicator(year, indicatorTimePayTax);



    }

//    public String checkNullData(String valueIndicator){
//        if(valueIndicator.equals("null")){
//        }
//
//    }


}

