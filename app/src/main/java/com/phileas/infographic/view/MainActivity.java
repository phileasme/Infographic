package com.phileas.infographic.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.phileas.infographic.R;
import com.phileas.infographic.controller.CountryAdapter;
import com.phileas.infographic.controller.ReadAllAssets;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private HorizontalBarChart timeToStartBusinessChart, registerBusinessChart;
    private PieChart pieChart;
    private ListView listView;
    private ArrayList<Country> countryName;
    private CountryAdapter countryAdapter;
    private Button btn2015;
    private Button btn2014;
    private int year=2015;
    private Pair<Country,Country> countryPair;
    Countries countries = new Countries();
    private Country country1;
    private Country country2;
    private CheckBox checkBox;
    private TextView textView;
    public TextView nullValues;
    private ChartsView chartsView;

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
        int counter = 0;
        for(Country country : countries.getCountries()){
            Log.i("country", "" + country.getName() + " " + counter);
            counter++;
        }

        countryName = countries.getCountries();

        country1 = countryName.get(99);
        country2 = countryName.get(196);

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
                    populateCharts(country1,country2);
                }
                else {
                    year = 2015;
                    populateCharts(country1,country2);
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
        populateCharts(country1,country2);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Total tax rate (% of commercial profits)");
        chartsView.pieChartLegend(pieChart);

    }

    public void populateCharts(Country countryOne, Country countryTwo) {

        chartsView = new ChartsView(countryOne, countryTwo, year);
        chartsView.addData();
        chartsView.checkNull(pieChart, nullValues);
        pieChart.setData(chartsView.addData());
        pieChart.invalidate();
        timeToStartBusinessChart = (HorizontalBarChart) findViewById(R.id.timeToStartBusinessChart);
        registerBusinessChart = (HorizontalBarChart) findViewById(R.id.registerBusinessChart);
        chartsView.dataBarChart("IC.REG.DURS");
        chartsView.dataBarChart("IC.REG.PROC");
        registerBusinessChart.setData(chartsView.dataBarChart("IC.REG.PROC"));
        timeToStartBusinessChart.setData(chartsView.dataBarChart("IC.REG.DURS"));
        timeToStartBusinessChart.invalidate();
        registerBusinessChart.invalidate();
        chartsView.setBarCharts(timeToStartBusinessChart);
        chartsView.setBarCharts(registerBusinessChart);

    }
}