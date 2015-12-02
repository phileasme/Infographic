package com.phileas.infographic.view;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.phileas.infographic.R;


import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

public class MainActivity extends Activity {


    private PieChart pieChart;
    private EditText editText;
    private ListView listView;


    private float [] yData = {50,23,10,50, 60};
    private String [] xData = {"Uk", "Singapor", "America", "China", "Austria"};


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.searchBox);
        listView = (ListView) findViewById(R.id.listCountries);


        pieChart=(PieChart) findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Total tax rate");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(7);
        pieChart.setTransparentCircleRadius(10);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
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

        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);


        BarChart barChart = (BarChart) findViewById(R.id.barChart);
        BarData data = new BarData(getXAxis(), getBarDataSet());
        barChart.setData(data);
        barChart.setDescription("Percentage of Poverty increase in the past years");
        barChart.animateXY(3000, 6000);
        barChart.setDragEnabled(true);
        barChart.setPinchZoom(true);
        barChart.setDrawGridBackground(false);


    }
    private ArrayList<BarDataSet> getBarDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> chnaEntries = new ArrayList<>();
        BarEntry chna1 = new BarEntry(35f, 0); //1990
        chnaEntries.add(chna1);
        BarEntry chna2 = new BarEntry(45f, 1); //1995
        chnaEntries.add(chna2);
        BarEntry chna3 = new BarEntry(52f, 2); //2000
        chnaEntries.add(chna3);
        BarEntry chna4 = new BarEntry(39f, 3); //2005
        chnaEntries.add(chna4);
        BarEntry chna5 = new BarEntry(51f, 4); //2010
        chnaEntries.add(chna5);
        BarEntry chna6 = new BarEntry(25f, 5); //2015
        chnaEntries.add(chna6);

        ArrayList<BarEntry> indEntries = new ArrayList<>();
        BarEntry ind1 = new BarEntry(65f, 0); //1990
        indEntries.add(ind1);
        BarEntry ind2 = new BarEntry(55f, 1); //1995
        indEntries.add(ind2);
        BarEntry ind3 = new BarEntry(62f, 2); //2000
        indEntries.add(ind3);
        BarEntry ind4 = new BarEntry(27f, 3); //2005
        indEntries.add(ind4);
        BarEntry ind5 = new BarEntry(65f, 4); //2010
        indEntries.add(ind5);
        BarEntry ind6 = new BarEntry(25f, 5); //2015
        indEntries.add(ind6);

        BarDataSet chnaDataSet = new BarDataSet(chnaEntries, "China");
        chnaDataSet.setColor(Color.DKGRAY);

        BarDataSet indDataSet = new BarDataSet(indEntries, "India");
        indDataSet.setColor(Color.RED);

        dataSets = new ArrayList<>();
        dataSets.add(chnaDataSet);
        dataSets.add(indDataSet);
        return dataSets;


    }

    private ArrayList<String> getXAxis() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("1990");
        xAxis.add("1995");
        xAxis.add("2000");
        xAxis.add("2005");
        xAxis.add("2010");
        xAxis.add("2010");
        return xAxis;
    }





    // add the data to the pie chart
    public void addData(){

        // data for the y values
        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i =0; i < yData.length; i++)
            yVals.add(new Entry(yData[i],i));

        // data for the x values
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);


        PieDataSet pieDataSet = new PieDataSet(yVals, "Countries");
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
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.GRAY);

        pieChart.setData(pieData);
        pieChart.highlightValue(null);
        pieChart.invalidate();


    }




}
