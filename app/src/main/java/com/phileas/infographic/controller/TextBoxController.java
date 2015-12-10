package com.phileas.infographic.controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.phileas.infographic.R;
import com.phileas.infographic.model.Country;
import com.phileas.infographic.view.MainActivity;

import java.util.ArrayList;

/**
 * Created by Hannah on 08/12/2015.
 */
public class TextBoxController {
    Country one;
    Country two;
    int year;
    TextView indicator1, indicator2, indicator3, indicator4, indicator5;
    String ind1, ind2, ind3, ind4, ind5;
    ArrayList<TextView> textviews;
    String nameOfCountry, nameOfCountry2;
    String text1, text2, text3, text4, text5;
    String data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;
    Double data1double, data2double, data3double, data4double, data5double, data6double, data7double, data8double, data9double, data10double;

    public TextBoxController(ArrayList<TextView> inds, Country a, Country b, int year) {
        one = a;
        two = b;
        this.year = year;
        textviews = inds;
    }


    public void setText() {


        ind1 = "IC.BUS.NREG";
        ind2 = "IC.BUS.EASE.XQ";
        ind3 = "IC.REG.PROC";
        ind4 = "IC.TAX.DURS";
        ind5 = "IC.REG.DURS";

        indicator1 = textviews.get(0);
        indicator2 = textviews.get(1);
        indicator3 = textviews.get(2);
        indicator4 = textviews.get(3);
        indicator5 = textviews.get(4);

        nameOfCountry = one.getName();
        nameOfCountry2 = two.getName();

        createText(one, two);

        indicator1.setText(nameOfCountry + " " + data1 + ", " + nameOfCountry2 + " " + data6);
        indicator2.setText(nameOfCountry + " " + data2 + " days, " + nameOfCountry2 + " " + data7 + " days.");
        indicator3.setText(nameOfCountry + " " + data3 + ", " + nameOfCountry2 + " " + data8);
        indicator4.setText(nameOfCountry + " " + data4 + " hours, " + nameOfCountry2 + " " + data9 + " hours.");
        indicator5.setText(nameOfCountry + " " + data5 + "%, " + nameOfCountry2 + " " + data10 + "%.");


    }

    public void createText(Country any, Country any1) {
        data1 = any.getIndicator(year, ind1);
        data2 = any.getIndicator(year, ind2);
        data3 = any.getIndicator(year, ind3);
        data4 = any.getIndicator(year, ind4);
        data5 = any.getIndicator(year, ind5);
        data6 = any1.getIndicator(year, ind1);
        data7 = any1.getIndicator(year, ind2);
        data8 = any1.getIndicator(year, ind3);
        data9 = any1.getIndicator(year, ind4);
        data10 = any1.getIndicator(year, ind5);

       // checkData();
    }

//    public void checkData() {
//
//        changeToDouble();
//
//        if (data1double == 0.0) {
//            data1 = "No data available";
//        }
//        if (data2double == 0.0) {
//            data2 = "No data available";
//        }
//        if (data3double == 0.0) {
//            data3 = "No data available";
//        }
//        if (data4double == 0.0) {
//            data4 = "No data available";
//        }
//        if (data5double == 0.0) {
//            data5 = "No data available";
//        }
//        if (data6double == 0.0) {
//            data6 = "No data available";
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
//        data1double = Double.parseDouble(data1);
//        data2double = Double.parseDouble(data2);
//        data3double = Double.parseDouble(data3);
//        data4double = Double.parseDouble(data4);
//        data5double = Double.parseDouble(data5);
//        data6double = Double.parseDouble(data6);
//        data7double = Double.parseDouble(data7);
//        data8double = Double.parseDouble(data8);
//        data9double = Double.parseDouble(data9);
//        data10double = Double.parseDouble(data10);
//
//
//            if (data1.equals("null")) {
//                data1double = 0.0;
//            } else {
//                data1double = data1double - data1double % 0.01;
//            }
//            if (data2.equals("null")) {
//                data2double = 0.0;
//            } else {
//                data2double = data2double - data2double % 0.01;
//            }
//            if (data3.equals("null")) {
//                data3double = 0.0;
//            } else {
//                data3double = data3double - data3double % 0.01;
//            }
//            if (data4.equals("null")) {
//                data4double = 0.0;
//            } else {
//                data4double = data4double - data4double % 0.01;
//            }
//            if (data5.equals("null")) {
//                data5double = 0.0;
//            } else {
//                data5double = data5double - data5double % 0.01;
//            }
//            if (data6.equals("null")) {
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

