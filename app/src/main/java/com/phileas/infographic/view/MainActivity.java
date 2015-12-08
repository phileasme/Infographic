package com.phileas.infographic.view;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
import com.phileas.infographic.controller.CountryAdapter;
import com.phileas.infographic.controller.PieChartData;
import com.phileas.infographic.controller.ReadAllAssets;
import com.phileas.infographic.model.Countries;
import com.phileas.infographic.model.Country;
import java.util.ArrayList;

public class MainActivity extends Activity  {

    private PieChart pieChart;
    private EditText editText;
    private ListView listView;
    private TextView textView;
    private CheckBox checkBox;
    private ArrayList<Country> countryName;
    private CountryAdapter countryAdapter;
    private Button btn2015;
    private Button btn2014;
    private int year=2015;

    Countries countries = new Countries();
    private float [] yData={5,10};
    private String [] xData = new String[2];

            @Override
            public void onCreate(final Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
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


                countryName = countries.getCountries();


                Country  countryOne = countryName.get(15);
                Country countryTwo = countryName.get(30);

                xData[0] = countryOne.getName();
                xData[1] = countryTwo.getName();

                countryAdapter = new CountryAdapter(this, android.R.layout.simple_list_item_1, countryName);

                listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(countryAdapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                btn2014 = (Button) findViewById(R.id.button2014);
                btn2015 = (Button) findViewById(R.id.button2015);

                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Button btn = (Button) findViewById(v.getId());
                        String yearS = (String) btn.getText();
                        if(yearS.equals("2014")){
                            year = 2014;
                        }
                        else{
                            year=2015;
                        }
                    }
                };

                btn2014.setOnClickListener(onClickListener);
                btn2015.setOnClickListener(onClickListener);

                editText = (EditText) findViewById(R.id.txt_search);
                textView = (TextView) findViewById(R.id.txt_listitem1);
                checkBox = (CheckBox) findViewById(R.id.checkbox);

                PieChartData pieChartData = new PieChartData(countryOne,countryTwo,year,"NE.EXP.GNFS.ZS");
                yData =  pieChartData.setData();

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

                pieChart =(PieChart) findViewById(R.id.pieChart2);
                pieChart.setUsePercentValues(true);
                pieChart.setDescription("Exports of goods and services (% of GDP)");

        addData();

        Legend legend = pieChart.getLegend();
                legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
                legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);
    }



    // add the data to the pie chart
    public void addData(){

        // data for the y values
        ArrayList<Entry> yVals = new ArrayList<>();
        for (int i =0; i < yData.length; i++)
            yVals.add(new Entry(yData[i],i));

        // data for the x values
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);


        PieDataSet pieDataSet = new PieDataSet(yVals, "Countries");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);

        // different colour for the enteries
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
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.BLACK);

        pieChart.setData(pieData);

        pieChart.highlightValue(null);
        pieChart.invalidate();


    }


}