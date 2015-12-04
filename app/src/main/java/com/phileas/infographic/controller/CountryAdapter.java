package com.phileas.infographic.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.phileas.infographic.R;
import com.phileas.infographic.model.Country;

import java.util.ArrayList;
public class CountryAdapter extends BaseAdapter implements Filterable {

    private ArrayList<String> countriesList;

    private ArrayList<String> filteredCountries;

    private LayoutInflater layoutInflater;

    private ValueFilter valueFilter;

    public CountryAdapter(ArrayList<String> countriesList, Context context) {

        this.countriesList=countriesList;

        this.filteredCountries=countriesList;

        layoutInflater=LayoutInflater.from(context);

        getFilter();
    }

    //How many items are in the data set represented by this Adapter.
    @Override
    public int getCount() {

        return countriesList.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public Object getItem(int position) {

        return countriesList.get(position);
    }

    //Get the row id associated with the specified position in the list.
    @Override
    public long getItemId(int position) {

        return position;
    }

    //Get a View that displays the data at the specified position in the data set.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView==null) {

            viewHolder=new ViewHolder();

            convertView=layoutInflater.inflate(R.layout.itemlist,null);

            viewHolder.textView=(TextView)convertView.findViewById(R.id.txt_listitem);

            convertView.setTag(viewHolder);

        }
        else {

            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(countriesList.get(position).toString());

        return convertView;
    }

    private class  ViewHolder{

        TextView textView;
    }

    //Returns a filter that can be used to constrain data with a filtering pattern.
    @Override
    public Filter getFilter() {

        if(valueFilter==null) {

            valueFilter=new ValueFilter();
        }

        return valueFilter;
    }


    private class ValueFilter extends Filter {


        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results=new FilterResults();

            if(constraint!=null && constraint.length()>0){

                ArrayList<String> filterList=new ArrayList<String>();

                for(int i=0;i<filteredCountries.size();i++){
                    

                    if(filteredCountries.get(i).contains(constraint)) {

                        filterList.add(filteredCountries.get(i));

                    }
                }


                results.count=filterList.size();

                results.values=filterList;

            }else{

                results.count=filteredCountries.size();

                results.values=filteredCountries;

            }

            return results;
        }


        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      Filter.FilterResults results) {

            countriesList=(ArrayList<String>)results.values;

            notifyDataSetChanged();


        }

    }

}