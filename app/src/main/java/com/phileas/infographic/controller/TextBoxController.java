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
    TextView textViewPayTax, textViewNewBusinessReg, textViewEaseOfBusiness, textViewExportsCountryOne, textViewExportsCountryTwo;
    String indicatorNewBusinessReg, indicatorEaseOfBusiness, indicatorTimePayTax, indicatorExports;
    ArrayList<TextView> textviews;
    String nameOfCountry, nameOfCountry2;
    String country1BusinessData, country1EaseData, country1TaxData, country2BusinessData, country2EaseData, country2TaxData, country1ExportData, country2ExportData;

    /**Constructor
     *
     * @param textViewArrayList
     * @param countrya
     * @param countryb
     * @param year
     */
    public TextBoxController(ArrayList<TextView> textViewArrayList, Country countrya, Country countryb, int year) {
        one = countrya;
        two = countryb;
        this.year = year;
        textviews = textViewArrayList;
    }



    /**Method that defines each TextView from the array and sets the text in the TextViews
     *
     */
    public void setText() {


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

        getData(one, two);

        //setting tax data
        checkNullDataTax();
        //setting ease of business data
        checkNullDataEase();
        //setting new business data
        checkNullDataBusiness();
        //setting exports data
        checkNullDataExports();


    }

    /**Method that stores the indicator name into a variable
     *
     */
    public void getIndicatorName(){
        //new business registered
        indicatorNewBusinessReg = "IC.BUS.NREG";
        //ease of doing business
        indicatorEaseOfBusiness = "IC.BUS.EASE.XQ";
        //time to pay taxes
        indicatorTimePayTax = "IC.TAX.DURS";
        //exports of goods and services (% of GDP)
        indicatorExports="NE.EXP.GNFS.ZS";

    }

    /**Method that gets data from the indicators and stores them in a String
     * @param countryOne
     * @param countryTwo
     */
    public void getData(Country countryOne, Country countryTwo) {

        //getting tax data for country1
        String c1bds = countryOne.getIndicator(year, indicatorNewBusinessReg);
        country1BusinessData = roundUpString(c1bds);

        //getting ease of business data for country 1
        String c1ead =countryOne.getIndicator(year, indicatorEaseOfBusiness);
        country1EaseData = roundUpString(c1ead);

        //getting new business data for country1
        String c1td = countryOne.getIndicator(year, indicatorTimePayTax);
        country1TaxData = roundUpString(c1td);

        //getting exports of goods data for country2
        String c1exd = countryOne.getIndicator(year, indicatorExports);
        country1ExportData = roundUpString(c1exd);




        //getting tax data for country2
        String c2bds= countryTwo.getIndicator(year, indicatorNewBusinessReg);
        country2BusinessData = roundUpString(c2bds);

        //getting ease of business data for country 2
        String c2ead= countryTwo.getIndicator(year, indicatorEaseOfBusiness);
        country2EaseData = roundUpString(c2ead);

        //getting new business registered for country2
        String c2td= countryTwo.getIndicator(year, indicatorTimePayTax);
        country2TaxData = roundUpString(c2td);

        //getting exports of goods data for country2
        String c2exd= countryTwo.getIndicator(year, indicatorExports);
        country2ExportData = roundUpString(c2exd);



    }

    /** Method that checks whether the String value of the Business indicator is null and
     * sets the text accordingly,
     * displaying the appropriate message.
     *
     */

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

    /** Method that checks whether the String value of the prepare and paying taxes indicator is null
     * and sets the text accordingly, displaying the appropriate message.
     *
     */
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

    /**
     * Method that checks whether the String value of the Ease of doing a business indicator is null
     * and sets the text accordingly, displaying the appropriate message.
     */
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

    /**
     * Method that checks whether the String value of the exporting of goods and services indicator is null and
     * sets the text accordingly, displaying the appropriate message.
     */
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

    /**Method that rounds up the string and shortens its length to 2 characters after the decimal.
     * @param c
     * @return shortened version of the string.
     */
    public String roundUpString(String c){
        if(c.indexOf('.') != -1){
            if(c.substring(c.indexOf('.'),c.length()-1).length()> 2){
                return c.substring(0,c.indexOf('.')+3);
            }
        }
        return  c;
    }

}