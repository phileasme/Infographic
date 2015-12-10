package com.phileas.infographic.view;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
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


    public ChartsView(Country countryOne, Country countryTwo, int year) {
        this.year = year;
        this.countryOne = countryOne;
        this.countryTwo = countryTwo;
    }


    // add the data to the pie chart
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
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.BLACK);

        pieDataSet.setColors(this.setColors());

        return pieData;


    }
    //Draws the legend for the pieChart
    public void pieChartLegend(PieChart pieChart) {
        this.pieChart = pieChart;
        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);
    }

    //Checks if there are null values, replaces the pieChart with a textView if that is the case
    public void checkNull(PieChart pieChart, TextView nullValues) {
        if (pieChartData.getNullValues()) {
            pieChart.setVisibility(View.INVISIBLE);
            nullValues.setText("Unfortunately, there is no data for Total tax rate (% of commercial profits)");
            nullValues.setVisibility(View.VISIBLE);

        } else if (pieChartData.getNullValue()) {
            pieChart.setVisibility(View.INVISIBLE);
            nullValues.setText("Unfortunately, there is no data for Total tax rate (% of commercial profits) for " + pieChartData.getCountryThatIsNull());
            nullValues.setVisibility(View.VISIBLE);
        }

    }

    //Populates the barChart
    public BarData dataBarChart(String indicator){

        this.indicator=indicator;
        String[] xAxis = new String[2];
        xAxis[0]=countryOne.getName();
        xAxis[1]=countryTwo.getName();

        countryOneValue= countryOne.getIndicator(year, indicator);
        countryTwoValue= countryTwo.getIndicator(year, indicator);

        valueSet = new ArrayList<>();

        ArrayList<BarDataSet>  dataSets = new ArrayList<>();

        if (countryOneValue.equals("null")){
            countryOneValue="0";

        } else if (countryTwoValue.equals("null")){
            countryTwoValue="0";
        }
        else if(countryOneValue.equals("null") && countryTwoValue.equals("null")){
            countryOneValue="0";
            countryTwoValue="0";
        }
        populateValueSet();

        BarDataSet yValueBarDataSet = new BarDataSet(valueSet,"");
        dataSets.add(yValueBarDataSet);

        BarData barData = new BarData(xAxis, dataSets);
        barData.setValueTextSize(10f);
        yValueBarDataSet.setColors(this.setColors());

        return barData;
    }

    //Method for adding colors to the charts
    public ArrayList<Integer> setColors(){
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        return colors;
    }

    public void populateValueSet(){
        valueSet.add(new BarEntry(Float.parseFloat(countryOneValue), 0));
        valueSet.add(new BarEntry(Float.parseFloat(countryTwoValue), 1));
    }


}