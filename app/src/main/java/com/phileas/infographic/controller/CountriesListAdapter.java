package com.phileas.infographic.controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phileas.infographic.R;

/**
 * Created by Shokryah on 29/11/2015.
 */
public class CountriesListAdapter extends Activity {
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public CountriesListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.list_of_countries, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_of_countries, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;

    };
}





}
