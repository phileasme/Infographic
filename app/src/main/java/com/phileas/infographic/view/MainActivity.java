package com.phileas.infographic.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.phileas.infographic.R;
import com.phileas.infographic.controller.CountryAdapter;
import com.phileas.infographic.controller.ReadAllAssets;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Countries countries;
    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Returns a defined Countries class filled with indicator information
        ReadAllAssets retrieveAllLocalCountriesInfo = new ReadAllAssets();
        countries = retrieveAllLocalCountriesInfo.ReadAllAssetFiles("",this.getBaseContext());
        /**
         *
         * Pass this to the main UI controller "countries.getCountries()" and that will give you a collection of countries.
         * Like this : UIMainController uiMainController = new UIMainController(countries.getCountries());
         * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         *                  Once you have made the UIMainController of course.
         */


        ArrayList<String> countryName = new ArrayList<String>();


        for (int i=0; i<countries.getCountries().size(); i++){
            String name = countries.getCountries().get(i).getName();
            countryName.add(name);
        }

        final CountryAdapter countryAdapter = new CountryAdapter(countryName, this.getBaseContext());
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(countryAdapter);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        EditText editText = (EditText) findViewById(R.id.txt_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    countryAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



        }
  
