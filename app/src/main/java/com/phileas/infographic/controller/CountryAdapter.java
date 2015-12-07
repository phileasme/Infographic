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
import android.widget.TextView;
import com.phileas.infographic.R;
import com.phileas.infographic.model.Country;
import java.util.ArrayList;


public class CountryAdapter extends ArrayAdapter<Country> implements Filterable {



    private ArrayList<Country> filteredCountries= new ArrayList<>();
    private ArrayList<String> countriesList= new ArrayList<>();


    private ValueFilter valueFilter;
    private ViewHolder viewHolder;
    private boolean[] itemChecked;
    int checked=0;


    public CountryAdapter(Context context, int resource, ArrayList<Country> a) {
        super(context, resource,a);

        for(int i=0; i<a.size(); i++)
        {
            this.countriesList.add(a.get(i).getName());
        }

                this.itemChecked = new boolean[a.size()];
                getFilter();
    }



    //Get the row id associated with the specified position in the list.
    @Override
    public long getItemId(int position) {

        return position;
    }

    //Get a View that displays the data at the specified position in the data set.
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Country country = getItem(position);

        viewHolder = null;
        Log.v("ConvertView", String.valueOf(position));

        if(convertView==null) {
            convertView =LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

            viewHolder=new ViewHolder();


            viewHolder.textView=(TextView)convertView.findViewById(R.id.txt_listitem1);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);


            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        itemChecked[position] = isChecked;
                }
            });

            convertView.setTag(viewHolder);

        viewHolder.checkBox.setChecked(itemChecked[position]);
        viewHolder.textView.setText(country.getName());
        return convertView;
    }

    private class  ViewHolder{

        TextView textView;
        CheckBox checkBox;

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
                    

                    if(filteredCountries.get(i).getName().contains(constraint)) {

                        filterList.add(filteredCountries.get(i).getName());

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