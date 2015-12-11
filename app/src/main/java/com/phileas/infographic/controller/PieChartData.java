package com.phileas.infographic.controller;
import android.util.Log;
import com.phileas.infographic.model.Country;
import com.phileas.infographic.view.MainActivity;


/**
 * Created by elizabetamukanova on 07/12/2015.
 * It sets the data for pie chart and bar charts
 * It checks the null values for the two countries
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
    String indicator ="IC.TAX.TOTL.CP.ZS";

    int year;
    float[] values = new float[2];

    /**Constructor
     *
     * @param country1
     * @param country2
     * @param year
     */
    public PieChartData(Country country1, Country country2, int year) {

        this.country1 = country1;
        this.country2 = country2;
        this.year = year;
    }


    /**Method that sets value1 and value2 to the data from indicators
     *Checks whether or not any of the data stored in value1 or value2 is null and if so calls method parseValues()
     * @return float array of values
     * @throws NumberFormatException
     * @throws NullPointerException
     */
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

    /**Method that converts the data in string form to a float and adds it to a float array
     *
     */
    private void parseValues() {
        theValue1 = Float.parseFloat(value1);
        theValue2 = Float.parseFloat(value2);
        values[0] = theValue1;
        values[1] = theValue2;
    }

    /** Method that checks if all of the values in the float array are null
     * @return value, if all of the float values in the array are null
     */
    public boolean getNullValues() {

        if (values[0] == 0 && values[1] == 0) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    /** Method that checks if either of the values in the float array are null
     * @return value, if one of the float values in the array is null or not null
     */
    public boolean getNullValue(){

        if (values[0]==0 || values[1]==0){
            value=true;
        }
        return value;
    }

    /**Method that displays a textview instead of the pie chart with a message when the country or countries have null data
     * @return countryNullName, name of the country that is null and countryNotNull, the name of country that is NOT null as string.
     */
    public String getCountryThatIsNull(){
        if(values[0]==0){
            countryNullName=country1.getName();
            countryNotNull=country2.getName() + " has " + values[1] + " total tax rate (% of commercial profits)";
        }
        else{
            countryNullName=country2.getName();
            countryNotNull=country1.getName()+ " has " + values[0] + " total tax rate (% of commercial profits)";;
        }
        return countryNullName + ". " + countryNotNull;
    }
}