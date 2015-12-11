package com.phileas.infographic.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.phileas.infographic.R;
import com.phileas.infographic.controller.ReadAllAssets;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.phileas.infographic.controller.TextBoxController;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {


    private ArrayList<Country> countriesArray;
    private ArrayList<String> countriesArrayName;
    private int count = 2;



    private HorizontalBarChart timeToStartBusinessChart, registerBusiness;
    private PieChart pieChart;
    private ListView listView;
    private ArrayList<Country> countryName;
    private Button btn2015;
    private Button btn2014;
    TextBoxController indicators;
    private ReadAllAssets retrieveAllLocalCountriesInfo;
    private Country countryA,countryB,previousCountryA,previousCountryB;
    private int year=2014;
    private Countries countries = new Countries();
    TextView indicator1, indicator2, indicator3, exportIndicatorCountryOne, exportIndicatorCountryTwo ;
    public TextView nullValues;
    private ChartsView chartsView;
    ArrayList<TextView> collectionOfTextviews;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Returns a defined Countries class filled with indicator information
        retrieveAllLocalCountriesInfo = new ReadAllAssets();

        ReadAllAssets retrieveAllLocalCountriesInfo = new ReadAllAssets();

        countries = retrieveAllLocalCountriesInfo.ReadAllAssetFiles("", this.getBaseContext());

        countriesArray = new ArrayList();
        countriesArrayName = new ArrayList();
                countriesArray = countries.getCountries();
        for( Country country : countriesArray){
            countriesArrayName.add(""+country.getName());
            Log.i("Bumb",""+country.getName());
        }
        Collections.sort(countriesArrayName);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countriesArrayName );
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView) view).getText().toString();
                if (count == 0) {
                    countryA = getCountryByName(countriesArrayName.get(position));
                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
                    count++;
                } else if (count == 1 && (countryA == null || countryB == null)) {
                    if (countryA == null) {
                        if(!countryB.equals(getCountryByName(countriesArrayName.get(position)))) {
                            countryA = getCountryByName(countriesArrayName.get(position));
                            Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
                            count++;
                            populateView(countryA, countryB);
                        }
                    } else if (countryB == null) {
                        if (!countryA.equals(getCountryByName(countriesArrayName.get(position)))) {
                            countryB = getCountryByName(countriesArrayName.get(position));
                            Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
                            count++;
                            populateView(countryA, countryB);
                        }
                    }
                } else if (count >= 2 ) {
                    if (countriesArrayName.get(position).equals(countryA.getName()) ||
                            countriesArrayName.get(position).equals(countryB.getName())) {
                        if (countriesArrayName.get(position).equals(countryA.getName())) {
                            previousCountryA = countryA;
                            countryA = null;
                        } else {
                            previousCountryB = countryB;
                            countryB = null;
                        }
                        count--;
                    }else{
                        Toast.makeText(getBaseContext(), "Please press the reset button.", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        Button btReset = (Button) findViewById(R.id.btReset);
        btReset.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           previousCountryA = countryA;
                                           previousCountryB = countryB;
                                           countryA = null;
                                           countryB = null;
                                           count= 0;
                                       }
                                   });

                countryName = countries.getCountries();

        countryA = countryName.get(181);
        countryB = countryName.get(3);

        indicator1 = (TextView)findViewById(R.id.payTaxData);
        indicator2 = (TextView)findViewById(R.id.newBusinessData);
        indicator3 = (TextView)findViewById(R.id.easeOfBusinessData);
        exportIndicatorCountryOne = (TextView) findViewById(R.id.exportsCountryOne);
        exportIndicatorCountryTwo = (TextView) findViewById(R.id.exportsCountryTwo);

        collectionOfTextviews= new ArrayList<>();
        collectionOfTextviews.add(indicator1);collectionOfTextviews.add(indicator2); collectionOfTextviews.add(indicator3);
        collectionOfTextviews.add(exportIndicatorCountryOne); collectionOfTextviews.add(exportIndicatorCountryTwo);



        View.OnClickListener buttonYearOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) findViewById(v.getId());
                String yearS = (String) btn.getText();
                //Safe guard.
                    if (yearS.equals("2014")) {
                        year = 2014;
                    } else {
                        year = 2015;
                    }
                Toast.makeText(getBaseContext(),""+year, Toast.LENGTH_SHORT).show();
                if((countryA != null && countryB != null)){
                    populateView(countryA, countryB);
                }else{
                    Toast.makeText(getBaseContext(),"Please select two countries.", Toast.LENGTH_LONG).show();
                }
            }
        };

        btn2014 = (Button) findViewById(R.id.button2014);
        btn2015 = (Button) findViewById(R.id.button2015);

        btn2014.setOnClickListener(buttonYearOnClickListener);
        btn2015.setOnClickListener(buttonYearOnClickListener);

        nullValues = (TextView) findViewById(R.id.nullValueTv);
        nullValues.setVisibility(View.INVISIBLE);


        pieChart = (PieChart) findViewById(R.id.pieChart2);
        populateView(countryA,countryB);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("");
        pieChart.getLegend().setEnabled(false);

    }

    private Country getCountryByName(String countryName){
            for(Country cx : countriesArray){
                if(cx.getName().equals(countryName)) return cx;
            }
        return null;
    }

    public void populateView(Country countryOne, Country countryTwo) {

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
        chartsView.setBarCharts(timeToStartBusinessChart);
        chartsView.setBarCharts(registerBusiness);
        indicators = new TextBoxController(collectionOfTextviews, countryOne, countryTwo, year);
        indicators.setText();


    }
}