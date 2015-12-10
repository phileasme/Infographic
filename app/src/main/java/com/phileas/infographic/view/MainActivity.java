package com.phileas.infographic.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieDataSet;

import com.phileas.infographic.R;
import com.phileas.infographic.controller.CountryAdapter;
import com.phileas.infographic.controller.ReadAllAssets;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private HorizontalBarChart timeToStartBusinessChart, registerBusiness;
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
    private ChartsView chartsView;
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

        countryOne = countryName.get(15);
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
                if (yearS.equals("2014")) {
                    year = 2014;
                    populateCharts();
                }
                else {
                    year = 2015;
                    populateCharts();
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
        populateCharts();
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Total tax rate (% of commercial profits)");
        chartsView.pieChartLegend(pieChart);



    }

    public void populateCharts() {

        chartsView = new ChartsView(countryOne, countryTwo, year);
        chartsView.addData();
        chartsView.checkNull(pieChart, nullValues);
        pieChart.setData(chartsView.addData());
        pieChart.invalidate();
        timeToStartBusinessChart = (HorizontalBarChart) findViewById(R.id.timeToStartBusinessChart);
        registerBusiness = (HorizontalBarChart) findViewById(R.id.registerBusinessChart);
        chartsView.dataBarChart("IC.REG.DURS");
        chartsView.dataBarChart("IC.REG.PROC");
        registerBusiness.setData(chartsView.dataBarChart("IC.REG.PROC"));
        timeToStartBusinessChart.setData(chartsView.dataBarChart("IC.REG.DURS"));
        timeToStartBusinessChart.invalidate();
        registerBusiness.invalidate();
        setBarCharts(timeToStartBusinessChart);
        setBarCharts(registerBusiness);

    }


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