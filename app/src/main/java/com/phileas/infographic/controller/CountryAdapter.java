package com.phileas.infographic.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phileas.infographic.R;
import com.phileas.infographic.model.Country;
import com.phileas.infographic.view.MainActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CountryAdapter extends ArrayAdapter<Country> {

    private ArrayList<String> countriesList = new ArrayList<>();
    private ViewHolder viewHolder;
    public boolean[] itemChecked;
    public ArrayList<View> convertedViews;
    int count = 0;

    public CountryAdapter(Context context, int resource, ArrayList<Country> a) {
        super(context, resource, a);

        for (int i = 0; i < a.size(); i++) {
            this.countriesList.add(a.get(i).getName());

        }
        Collections.sort(countriesList);
        this.itemChecked = new boolean[a.size()];
        convertedViews = new ArrayList<View>();
    }


    //Get the row id associated with the specified position in the list.
    @Override
    public long getItemId(int position) {

        return position;
    }

    //Get a View that displays the data at the specified position in the data set.
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Country country = getItem(position);

        viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        viewHolder = new ViewHolder();

        viewHolder.textView = (TextView) convertView.findViewById(R.id.txt_listitem1);
        convertView.setTag(viewHolder);
        viewHolder.textView.setText(country.getName());
        convertedViews.add(convertView);
        return convertView;
    }

    public int numbOfItems(){
        return countriesList.size();
    }
    private void createCheckHolder(){
        itemChecked = new boolean[getCount()];
    }

    public class ViewHolder {

        TextView textView;


    }



}