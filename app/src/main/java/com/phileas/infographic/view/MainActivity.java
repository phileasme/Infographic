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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.phileas.infographic.R;
import com.phileas.infographic.controller.ValueAdapter;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

public class MainActivity extends Activity {
    private ListView mSearchNFilterLv;

    private EditText mSearchEdt;

    private ArrayList<String> mStringList;

    private ValueAdapter valueAdapter;

    private TextWatcher mSearchTw;
    private RelativeLayout mainLayout;

    private PieChart mChart;
    private float [] yData = {50,23,10,50, 60};
    private String [] xData = {"Uk", "Singapor", "America", "China", "Austria"};



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        mSearchNFilterLv = (ListView) findViewById(R.id.list_view);

        mChart = new PieChart(this);


        //setContentView(mChart);
        mChart.setUsePercentValues(true);

        mChart.setDescription("Total tax rate");

        mChart.setDrawHoleEnabled(true);

        mChart.setHoleColorTransparent(true);

        mChart.setHoleRadius(7);

        mChart.setTransparentCircleRadius(10);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if (entry == null)
                    return;

                //Toast.makeText(MainActivity.this,xData[entry.getXIndex()] + "=" + entry.getVal()+ "%", Toast.LENGTH_SHORT.());
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();


        Legend legend = mChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);
        legend.setMaxSizePercent(0.30f);


        initUI();

        initData();

//        valueAdapter = new ValueAdapter(mStringList,this);
//
//        mSearchNFilterLv.setAdapter(valueAdapter);
//
//        mSearchEdt.addTextChangedListener(mSearchTw);


    }
    private  void addData(){
        ArrayList<Entry> yVals = new ArrayList<>();

        for (int i =0; i < yData.length; i++)
            yVals.add(new Entry(yData[i],i));


        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        PieDataSet pieDataSet = new PieDataSet(yVals, "tax rate");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);

        ArrayList<Integer> colours = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colours.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colours.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colours.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colours.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colours.add(c);

       colours.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colours);

        PieData pieData = new PieData(xVals, pieDataSet);
        pieData.setValueFormatter( new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.GRAY);

        mChart.setData(pieData);

        mChart.highlightValue(null);
        mChart.invalidate();


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
