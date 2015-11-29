package com.phileas.infographic.controller;

import java.util.ArrayList;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.phileas.infographic.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView list;
    String[] itemname = {"China", "India", "England"};
    Integer[] imgid = {R.drawable.china, R.drawable.india, R.drawable.england};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CountriesListAdapter adapter = new CountriesListAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        barChart();

    }


    public void barChart(){
        BarChart barChart = (BarChart) findViewById(R.id.barChart);
        BarData data = new BarData(getXAxis(), getBarDataSet());
        barChart.setData(data);
        barChart.setDescription("Percentage of Poverty increase in the past years");
        barChart.animateXY(3000, 6000);
        barChart.setDragEnabled(true);
        barChart.setPinchZoom(true);


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


}