package com.phileas.infographic.view;

import android.graphics.Color;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
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
public class PieChartView extends MainActivity{
    PieDataSet pieDataSet;
    PieData pieData;
    private int year;
    PieChartData pieChartData;
    Country countryOne;
    Country countryTwo;
    float [] yData = new float[2];
    PieChart pieChart;

    public PieChartView(Country countryOne, Country countryTwo, int year) {
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

        ArrayList<Integer> colours = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colours.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colours.add(c);
        colours.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colours);

        return pieData;


    }

    public void pieChartLegend(PieChart pieChart) {
        this.pieChart = pieChart;
        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);
    }


    public void checkNull(PieChart pieChart, TextView nullValues) {
        if (pieChartData.getNullValues()) {
            pieChart.setVisibility(View.INVISIBLE);
            nullValues.setText("Unfortunately, there is no data for the exports " +
                    "of goods and services for the chosen countries.");
            nullValues.setVisibility(View.VISIBLE);

        } else if (pieChartData.getNullValue()) {
            pieChart.setVisibility(View.INVISIBLE);
            nullValues.setText("Unfortunately, there is no data for the exports " +
                    "of goods and services for " + pieChartData.getCountryThatIsNull());
            nullValues.setVisibility(View.VISIBLE);
        }


    }
}
