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


       // checkData();
    }

//    public void checkData() {
//
//        changeToDouble();
//
//        if (data1double == 0.0) {
//            country1BusinessData = "No data available";
//        }
//        if (data2double == 0.0) {
//            country1EaseData = "No data available";
//        }
//        if (data3double == 0.0) {
//            country1TaxData = "No data available";
//        }
//        if (data4double == 0.0) {
//            country2BusinessData = "No data available";
//        }
//        if (data5double == 0.0) {
//            country2EaseData = "No data available";
//        }
//        if (data6double == 0.0) {
//            country2TaxData = "No data available";
//        }
//        if (data7double == 0.0) {
//            data7 = "No data available";
//        }
//        if (data8double == 0.0) {
//            data8 = "No data available";
//        }
//        if (data9double == 0.0) {
//            data9 = "No data available";
//        }
//        if (data10double == 0.0) {
//            data10 = "No data available";
//        }
//
//
//    }
//
//    public void changeToDouble() {
//        try {
//        data1double = Double.parseDouble(country1BusinessData);
//        data2double = Double.parseDouble(country1EaseData);
//        data3double = Double.parseDouble(country1TaxData);
//        data4double = Double.parseDouble(country2BusinessData);
//        data5double = Double.parseDouble(country2EaseData);
//        data6double = Double.parseDouble(country2TaxData);
//        data7double = Double.parseDouble(data7);
//        data8double = Double.parseDouble(data8);
//        data9double = Double.parseDouble(data9);
//        data10double = Double.parseDouble(data10);
//
//
//            if (country1BusinessData.equals("null")) {
//                data1double = 0.0;
//            } else {
//                data1double = data1double - data1double % 0.01;
//            }
//            if (country1EaseData.equals("null")) {
//                data2double = 0.0;
//            } else {
//                data2double = data2double - data2double % 0.01;
//            }
//            if (country1TaxData.equals("null")) {
//                data3double = 0.0;
//            } else {
//                data3double = data3double - data3double % 0.01;
//            }
//            if (country2BusinessData.equals("null")) {
//                data4double = 0.0;
//            } else {
//                data4double = data4double - data4double % 0.01;
//            }
//            if (country2EaseData.equals("null")) {
//                data5double = 0.0;
//            } else {
//                data5double = data5double - data5double % 0.01;
//            }
//            if (country2TaxData.equals("null")) {
//                data6double = 0.0;
//            } else {
//                data6double = data6double - data6double % 0.01;
//            }
//            if (data7.equals("null")) {
//                data7double = 0.0;
//            } else {
//                data7double = data7double - data7double % 0.01;
//            }
//            if (data8.equals("null")) {
//                data8double = 0.0;
//            } else {
//                data7double = data7double - data7double % 0.01;
//            }
//            if (data9.equals("null")) {
//                data9double = 0.0;
//            } else {
//                data9double = data9double - data9double % 0.01;
//            }
//            if (data10.equals("null")) {
//                data10double = 0.0;
//            } else {
//                data10double = data10double - data10double % 0.01;
//            }
//        } catch (NullPointerException e) {
//            Log.i("Exception " + e.getMessage(), e + "");
//        }

   // }
}

