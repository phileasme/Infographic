package com.phileas.infographic.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.phileas.infographic.R;
import com.phileas.infographic.controller.CountryAdapter;
import com.phileas.infographic.controller.ReadAllAssets;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<Country> countriesArray;
    private CountryAdapter countryAdapter;
    private boolean itemChecked[] ;
    Countries countries = new Countries();
    private Pair<Country,Country> selectedCountries;
    private ArrayList<String> name;
    private int count = 0;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedCountries = new Pair(null, null);

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
        countriesArray = countries.getCountries();

        countryAdapter = new CountryAdapter(this, android.R.layout.simple_list_item_1, countriesArray);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(countryAdapter);

        itemChecked = countryAdapter.itemChecked;

        Log.i("IDOFSOMETHING", "" + countryAdapter.getItemId(1));
        listView.setItemsCanFocus(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {

                Log.i("County", "" + countryAdapter.getItem(position).getName());
                if (count == 0 && !countriesArray.get(position).isSelected()) {
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    TextView txCountryA = (TextView) findViewById(R.id.tvCountryA);
                    txCountryA.setText(countryAdapter.getItem(position).getName());
                    count++;
                    countriesArray.get(position).setSelected(true);
                } else if (count == 1 && !countriesArray.get(position).isSelected()) {
                    count++;
                    view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    TextView txCountryB = (TextView) findViewById(R.id.tvCountryB);
                    txCountryB.setText(countryAdapter.getItem(position).getName());
                    countriesArray.get(position).setSelected(true);
                } else if (countriesArray.get(position).isSelected()) {
                    count--;
                    countriesArray.get(position).setSelected(false);

                    view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
                if (count == 2) {
                    Stack<Country> countries2 = new Stack<Country>();
                    for (Country x : countriesArray) {
                        if (x.isSelected()) countries2.push(x);
                    }
                    selectedCountries = new Pair(countries2.pop(), countries2.pop());
                    Log.i("countrie12", "" + selectedCountries.first.getName() + " " + selectedCountries.second.getName());
                    /** TODO: USE METHODS HERE !! **/

                }

            }
        });

    }




}