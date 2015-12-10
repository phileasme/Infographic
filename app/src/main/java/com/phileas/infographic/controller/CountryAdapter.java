package com.phileas.infographic.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.phileas.infographic.R;
import com.phileas.infographic.model.Country;
import java.util.ArrayList;


public class CountryAdapter extends ArrayAdapter<Country> {

    private ArrayList<String> countriesList = new ArrayList<>();
    private ViewHolder viewHolder;
    public boolean[] itemChecked;
    int count = 0;


    public CountryAdapter(Context context, int resource, ArrayList<Country> a) {
        super(context, resource, a);

        for (int i = 0; i < a.size(); i++) {
            this.countriesList.add(a.get(i).getName());

        }
        this.itemChecked = new boolean[a.size()];

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
        System.out.println(country + "position of country");
        viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        viewHolder = new ViewHolder();


        viewHolder.textView = (TextView) convertView.findViewById(R.id.txt_listitem1);
        viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                itemChecked[position] = isChecked;

                if (isChecked && !country.isSelected()) {
                    System.out.println(country + "country pos");
                    if (count >= 2) {
                        buttonView.setChecked(false);
                        return;
                    }
                    country.setSelected(true);
                    count++;
                } else if (!isChecked && country.isSelected()) {
                    country.setSelected(false);
                    count--;

                }
            }
        });

        convertView.setTag(viewHolder);
        viewHolder.checkBox.setChecked(itemChecked[position]);
        viewHolder.textView.setText(country.getName());
        return convertView;
    }
    private void createCheckHolder(){
        itemChecked = new boolean[getCount()];
    }

    private class ViewHolder {

        TextView textView;
        CheckBox checkBox;

    }
}

