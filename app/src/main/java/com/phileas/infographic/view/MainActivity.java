package com.phileas.infographic.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private TextView textView;
    private CheckBox checkBox;
    private ArrayList<Country> countryName;
    private CountryAdapter countryAdapter;
    private Button btn2015;
    private Button btn2014;
    private int year = 2014;
    private PieChartData pieChartData;
    private TextView nullValues;
    private Country countryOne;
    private Country countryTwo;

    Countries countries = new Countries();
    private float[] yData = {5, 10};
    private String[] xData = new String[2];

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


        countryName = countries.getCountries();


         countryOne = countryName.get(0);
         countryTwo = countryName.get(3);

        xData[0] = countryOne.getName();
        xData[1] = countryTwo.getName();

        countryAdapter = new CountryAdapter(this, android.R.layout.simple_list_item_1, countryName);


        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(countryAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        btn2014 = (Button) findViewById(R.id.button2014);
        btn2015 = (Button) findViewById(R.id.button2015);

        pieChartData = new PieChartData(countryOne, countryTwo, year);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(v.getId());
                String yearS = (String) btn.getText();
                Log.i("The year is ", yearS);
                if (yearS.equals("2014")) {
                    year = 2014;
                    pieChartData = new PieChartData(countryOne, countryTwo, year);
                    yData = pieChartData.setData();
                    addData();
                    checkNull();
                    pieChart.invalidate();
                } else {
                    year = 2015;
                    pieChartData = new PieChartData(countryOne, countryTwo, year);
                    yData = pieChartData.setData();
                    addData();
                    checkNull();
                    pieChart.invalidate();

                }
            }
        };

        btn2014.setOnClickListener(onClickListener);
        btn2015.setOnClickListener(onClickListener);

        textView = (TextView) findViewById(R.id.txt_listitem1);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        nullValues = (TextView) findViewById(R.id.nullValueTv);
        nullValues.setVisibility(View.INVISIBLE);

        yData = pieChartData.setData();
        pieChart = (PieChart) findViewById(R.id.pieChart2);

        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Exports of goods and services (% of GDP)");


        addData();
        pieChartLegend();
        checkNull();

    }


    // add the data to the pie chart
    public void addData() {

        // data for the y values
        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i = 0; i < yData.length; i++)
            yVals.add(new Entry(yData[i], i));

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
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colours.add(c);


        colours.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colours);

        PieData pieData = new PieData(xVals, pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.BLACK);

        pieChart.setData(pieData);

        pieChart.highlightValue(null);
        pieChart.invalidate();


    }

    public void checkNull() {
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

    public void pieChartLegend() {
        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);
    }


}