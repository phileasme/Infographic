package com.phileas.infographic.view;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.phileas.infographic.R;
import com.phileas.infographic.controller.ValueAdapter;
import com.phileas.infographic.controller.Charts;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

public class MainActivity extends Activity {
    private ListView mSearchNFilterLv;

    private EditText mSearchEdt;

    private ArrayList<String> mStringList;

    private ValueAdapter valueAdapter;

    private TextWatcher mSearchTw;
    private LinearLayout chartLayout;

    private PieChart mChart;
    private BarChart barChart;


    private float [] yData = {50,23,10,50, 60};
    private String [] xData = {"Uk", "Singapor", "America", "China", "Austria"};


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // provide data
        Charts charts = new Charts(yData, xData);



        initUI();

        initData();

//        valueAdapter = new ValueAdapter(mStringList,this);
//
//        mSearchNFilterLv.setAdapter(valueAdapter);
//
//        mSearchEdt.addTextChangedListener(mSearchTw);



    }

    private void initData() {

        mStringList=new ArrayList<String>();

        mStringList.add("one");

        mStringList.add("two");

        mStringList.add("three");

        mStringList.add("four");

        mStringList.add("five");

        mStringList.add("six");

        mStringList.add("seven");

        mStringList.add("eight");

        mStringList.add("nine");

        mStringList.add("ten");

        mStringList.add("eleven");

        mStringList.add("twelve");

        mStringList.add("thirteen");

        mStringList.add("United States of America");

        mSearchTw=new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                valueAdapter.getFilter().filter(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

    }

    private void initUI() {

        mSearchNFilterLv=(ListView) findViewById(R.id.list_view);

        mSearchEdt=(EditText) findViewById(R.id.txt_search);
    }

}
