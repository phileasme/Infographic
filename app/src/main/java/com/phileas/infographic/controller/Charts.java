package com.phileas.infographic.controller;

import android.app.Activity;
import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.phileas.infographic.R;

import java.util.ArrayList;

/**
 * Created by Shokryah on 29/11/2015.
 */
public class Charts extends Activity {

    private PieChart mChart;

    private float [] yData;
    private String [] xData;

   // private float [] yData = {50,23,10,50, 60};
   // private String [] xData = {"Uk", "Singapor", "America", "China", "Austria"};

    public Charts(float yData, String xData){
        xData = xData;
        yData = yData;

        mChart = (PieChart) findViewById(R.id.pieChart);
        mChart.setUsePercentValues(true);
        mChart.setDescription("Total tax rate");
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if (entry == null)
                    return;
                //  Toast.makeText(MainActivity.this,xData[entry.getXIndex()] + "=" + entry.getVal()+ "%", Toast.LENGTH_SHORT
            }
            @Override
            public void onNothingSelected() {

            }
        });

        addData();

        Legend legend = mChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);


    }



    // add the data to the pie chart
    private  void addData(){

        // data for the y values
        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i =0; i < yData.length; i++)
            yVals.add(new Entry(yData[i],i));

        // data for the x values
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);


        PieDataSet pieDataSet = new PieDataSet(yVals, "tax rate");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);

        // different colour for the enteries
        ArrayList<Integer> colours = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colours.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colours.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colours.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colours.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colours.add(c);

        colours.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colours);

        PieData pieData = new PieData(xVals, pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.GRAY);

        mChart.setData(pieData);

        mChart.highlightValue(null);
        mChart.invalidate();


    }

}
