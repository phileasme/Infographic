package com.phileas.infographic.view;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import com.phileas.infographic.controller.PieChartData;
import com.phileas.infographic.model.Country;

/**
 * Created by elizabetamukanova on 10/12/2015.
 * ChartsView class is used to populate the bar charts and the pie chart with
 * data for the countries that have been chosen.
 */
public class ChartsView extends MainActivity{
    PieDataSet pieDataSet;
    PieData pieData;
    private int year;
    PieChartData pieChartData;
    Country countryOne;
    Country countryTwo;
    float [] yData = new float[2];
    PieChart pieChart;
    private ArrayList<BarEntry> valueSet;
    private String countryOneValue;
    private String countryTwoValue;
    private String indicator;


    /**
     *  Constructor that initialise the countries and the year
     * @param countryOne
     * @param countryTwo
     * @param year
     */
    public ChartsView(Country countryOne, Country countryTwo, int year) {
        this.year = year;
        this.countryOne = countryOne;
        this.countryTwo = countryTwo;
    }



    /**Method that adds data to the pie chart
     * @return the data for the pie chart
     */
    public PieData addData() {

        pieChartData = new PieChartData(countryOne,countryTwo,year);

        ArrayList<Entry> yVals = new ArrayList<>();
        yData = pieChartData.setData();

        yVals.add(new Entry(yData[0], 0));
        yVals.add(new Entry(yData[1], 1));


        // data for the x values
        String [] xVals = new String[2];
        xVals[0] = countryOne.getName();
        xVals[1] = countryTwo.getName();


        pieDataSet = new PieDataSet(yVals, "Countries");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);

        pieData = new PieData(xVals, pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);

        pieDataSet.setColors(this.setColors());

        return pieData;


    }


    /**Method that checks if there are null values and if thats the case, it replaces the pieChart with a textvie.
     * @param pieChart
     * @param nullValues
     */
    public void checkNull(PieChart pieChart, TextView nullValues) {
        if (pieChartData.getNullValues()) {
            pieChart.setVisibility(View.INVISIBLE);
            nullValues.setText("Unfortunately, there is no data for Total tax rate (% of commercial profits)");
            nullValues.setVisibility(View.VISIBLE);

        } else if (pieChartData.getNullValue()) {
            pieChart.setVisibility(View.INVISIBLE);
            nullValues.setText(Html.fromHtml("<small> Unfortunately, there is no data for Total tax rate (% of commercial profits) for " + pieChartData.getCountryThatIsNull() + "</small>"));
            nullValues.setVisibility(View.VISIBLE);
        } else {
            pieChart.setVisibility(View.VISIBLE);
            nullValues.setVisibility(View.INVISIBLE);
        }

    }

    /**Method that populates the barChart
     * @param indicator
     * @return the data for the BarChart
     */
    public BarData dataBarChart(String indicator){

        this.indicator=indicator;
        String[] xAxis = new String[2];
        xAxis[0]=countryOne.getName();
        xAxis[1]=countryTwo.getName();

        countryOneValue= countryOne.getIndicator(year, indicator);
        countryTwoValue= countryTwo.getIndicator(year, indicator);

        valueSet = new ArrayList<>();

        ArrayList<BarDataSet>  dataSets = new ArrayList<>();

        if (countryOneValue.equals("null") && !countryTwoValue.equals("null")){
            countryOneValue="0";

        } else if (countryTwoValue.equals("null") && !countryOneValue.equals("null")){
            countryTwoValue="0";
        }
        else if(countryOneValue.equals("null") && countryTwoValue.equals("null")){
            countryOneValue="0";
            countryTwoValue="0";
        }
        else{
            countryOneValue = countryOne.getIndicator(year, indicator);
            countryTwoValue= countryTwo.getIndicator(year, indicator);
        }

        populateValueSet();

        BarDataSet yValueBarDataSet = new BarDataSet(valueSet,"");
        dataSets.add(yValueBarDataSet);

        BarData barData = new BarData(xAxis, dataSets);
        barData.setValueTextSize(12f);
        yValueBarDataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        return barData;
    }


    /** Method for adding colours to pie chart
     * @return Arraylist of colours
     */
    public ArrayList<Integer> setColors(){
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        return colors;
    }

    /**  Method that populates both of the bar charts with data
     *
     */
    public void populateValueSet(){

        float floatCountryOne = Float.parseFloat(countryOneValue);
        float floatCountryTwo = Float.parseFloat(countryTwoValue);

        BarEntry barEntryOne = new BarEntry(floatCountryOne,0);
        BarEntry barEntryTwo = new BarEntry(floatCountryTwo,1);
        valueSet.add(barEntryOne);
        valueSet.add(barEntryTwo);
    }


    /** Method that sets the design and the position of  both bar charts
     * @param horizontalBarChart
     */
    public void setBarCharts(HorizontalBarChart horizontalBarChart){

        horizontalBarChart.setDescription("");

        XAxis barChartXAxis = horizontalBarChart.getXAxis();
        barChartXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChartXAxis.setDrawAxisLine(true);
        barChartXAxis.setDrawGridLines(true);
        barChartXAxis.setGridLineWidth(0.3f);

        horizontalBarChart.getAxisRight().setEnabled(false);
        horizontalBarChart.getAxisLeft().setEnabled(false);

        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.getLegend().setEnabled(false);
    }




}