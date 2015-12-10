package com.phileas.infographic.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.phileas.infographic.R;
import com.phileas.infographic.controller.CountryAdapter;
import com.phileas.infographic.controller.PieChartData;
import com.phileas.infographic.controller.ReadAllAssets;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends Activity {

    private PieChart pieChart;
    private ListView listView;
    private ArrayList<Country> countryName;
    private CountryAdapter countryAdapter;
    private Button btn2015;
    private Button btn2014;
    private int year=2015;
    private Pair<Country,Country> countryPair;
    Countries countries = new Countries();
    private float [] yData;
    private String [] xData = new String[2];
    private Country countryOne;
    private Country countryTwo;
    private CheckBox checkBox;
    private TextView textView;
    public TextView nullValues;
    private PieChartView pieChartView;
    private PieDataSet pieDataSet;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Returns a defined Countries class filled with indicator information
        ReadAllAssets retrieveAllLocalCountriesInfo = new ReadAllAssets();
        countries = retrieveAllLocalCountriesInfo.ReadAllAssetFiles("", this.getBaseContext());
        /**
         *
         * Pass this to the main UI controller "countries.getCountries()" and that will give you a collection of countries.
         * Like this : UIMainController uiMainController = new UIMainController(countries.getCountries());
         * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         *                  Once you have made the UIMainController of course.
         */



        countryPair = new Pair<>(countryOne,countryTwo);
        countryName = countries.getCountries();

        countryOne = countryName.get(4);
        countryTwo = countryName.get(3);



        countryAdapter = new CountryAdapter(this, android.R.layout.simple_list_item_1, countryName);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(countryAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        View.OnClickListener buttonYearOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(v.getId());
                String yearS = (String) btn.getText();
                Log.i("The year is ", yearS);
                if (yearS.equals("2014")) {
                    year = 2014;
                    pieChartView = new PieChartView(countryOne, countryTwo, year);
                    pieChartView.addData();
                    pieChartView.checkNull(pieChart, nullValues);
                    pieChart.setData(pieChartView.addData());
                    pieChart.invalidate();
                }
                else {
                    year = 2015;
                    pieChartView = new PieChartView(countryOne, countryTwo, year);
                    pieChartView.addData();
                    pieChartView.checkNull(pieChart, nullValues);
                    pieChart.setData(pieChartView.addData());
                    pieChart.invalidate();

                }
            }
        };

        btn2014 = (Button) findViewById(R.id.button2014);
        btn2015 = (Button) findViewById(R.id.button2015);

        btn2014.setOnClickListener(buttonYearOnClickListener);
        btn2015.setOnClickListener(buttonYearOnClickListener);

        textView = (TextView) findViewById(R.id.txt_listitem1);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        nullValues = (TextView) findViewById(R.id.nullValueTv);
        nullValues.setVisibility(View.INVISIBLE);


        pieChart = (PieChart) findViewById(R.id.pieChart2);
        pieChartView = new PieChartView(countryOne,countryTwo, year);
        pieChartView.addData();
        pieChartView.checkNull(pieChart, nullValues);
        pieChart.setData(pieChartView.addData());
        pieChart.invalidate();
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Exports of goods and services (% of GDP)");
        pieChartView.pieChartLegend(pieChart);



    }


}