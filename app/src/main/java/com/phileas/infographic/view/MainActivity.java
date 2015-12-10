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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.phileas.infographic.R;
import com.phileas.infographic.controller.CountryAdapter;
import com.phileas.infographic.controller.ReadAllAssets;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<Country> countriesArray;
    Countries countries = new Countries();
    private Country countryA;
    private Country countryB;
    private  ReadAllAssets retrieveAllLocalCountriesInfo;
    private ArrayList<String> countriesArrayName;
    private int count = 0;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Returns a defined Countries class filled with indicator information
        retrieveAllLocalCountriesInfo = new ReadAllAssets();
        countries = retrieveAllLocalCountriesInfo.ReadAllAssetFiles("", this.getBaseContext());
        /**
         *
         * Pass this to the main UI controller "countries.getCountries()" and that will give you a collection of countries.
         * Like this : UIMainController uiMainController = new UIMainController(countries.getCountries());
         * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         *                  Once you have made the UIMainController of course.
         */
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
                    TextView txCountryA = (TextView) findViewById(R.id.tvCountryA);
                    txCountryA.setText(countriesArrayName.get(position));
                    Toast.makeText(getBaseContext(),item,Toast.LENGTH_LONG).show();
                    count++;
                } else if (count == 1) {
                    if (countryA == null) {
                        countryA = getCountryByName(countriesArrayName.get(position));
                        TextView txCountryA = (TextView) findViewById(R.id.tvCountryA);
                        txCountryA.setText(countriesArrayName.get(position));
                        Toast.makeText(getBaseContext(),item,Toast.LENGTH_LONG).show();
                    } else if(countryB == null){
                        countryB = getCountryByName(countriesArrayName.get(position));
                         TextView txCountryB = (TextView) findViewById(R.id.tvCountryB);
                        txCountryB.setText(countriesArrayName.get(position));
                        Toast.makeText(getBaseContext(),item,Toast.LENGTH_LONG).show();
                     }
                    count++;
                    /**Method thing(countryA,countryB) **/
                } else if (count >= 2 && (countriesArrayName.get(position).equals(countryA.getName()) ||
                        countriesArrayName.get(position).equals(countryB.getName()))) {
                    if (countriesArrayName.get(position).equals(countryA.getName())) {
                        countryA = null;
                    } else {
                        countryB = null;
                    }
                    count --;
                }


         }
        });

    }

    private Country getCountryByName(String countryName){
       for (Country cx : countriesArray) {
         if (cx.getName().equals(countryName)) return cx;}
        return null;
    }



}