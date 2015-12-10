package com.phileas.infographic.view;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private PieChart pieChart1;
    private ArrayList<Country> countryName;
    private CountryAdapter countryAdapter;
    private Button btn2015;
    private Button btn2014;
    private int year=2014;
    private PieChartData pieChartData;
    private TextView nullValues;

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

                Country  countryOne;
                Country countryTwo;
                TextView indicator1 = (TextView)findViewById(R.id.indicator1);
                TextView indicator2 = (TextView)findViewById(R.id.indicator2);
                TextView indicator3 = (TextView)findViewById(R.id.indicator3);
                TextView indicator4 = (TextView)findViewById(R.id.indicator4);
                TextView indicator5 = (TextView)findViewById(R.id.indicator5);
                TextView indicator6 = (TextView)findViewById(R.id.indicator6);

                countryName = countries.getCountries();


                Country  countryOne = countryName.get(15);
                Country countryTwo = countryName.get(30);

                xData[0] = countryOne.getName();
                xData[1] = countryTwo.getName();

                countryAdapter = new CountryAdapter(this, android.R.layout.simple_list_item_1, countryName);

                yData =  pieChartData.setData();


//                Before button click set text to..
//                indicator1.setText("Choose a date");
//                indicator2.setText("Choose a date");
//                indicator3.setText("Choose a date");
//                indicator4.setText("Choose a date");
//                indicator5.setText("Choose a date");
//                indicator6.setText("Choose a date");


                String ind1 = "IC.BUS.NREG"; String ind2 = "IC.REG.DURS";
                String ind3 = "IC.REG.PROC"; String ind4 = "IC.TAX.DURS";
                String ind5 = "IC.TAX.TOTL.CP.ZS"; String ind6 = "IC.BUS.EASE.XQ";


                indicator1.setText(countryOne.getName() + " " +countryOne.getIndicator(2015, ind1) + ", " + countryTwo.getName() + " " + countryTwo.getIndicator(2015, ind1));
                indicator2.setText(countryOne.getName() + " "+countryOne.getIndicator(2015, ind2) + " days, "+countryTwo.getName() +" "+countryTwo.getIndicator(2015, ind2)+ " days.");
                indicator3.setText(countryOne.getName() + " "+countryOne.getIndicator(2015, ind3) + ", "+countryTwo.getName() +" "+countryTwo.getIndicator(2015, ind3));
                indicator4.setText(countryOne.getName() + " "+countryOne.getIndicator(2015, ind4) + " hours, "+countryTwo.getName() +" "+countryTwo.getIndicator(2015, ind4)+" hours.");
                indicator5.setText(countryOne.getName() + " "+countryOne.getIndicator(2015, ind5) + "%, "+countryTwo.getName() +" "+countryTwo.getIndicator(2015, ind5)+"%.");
                indicator6.setText(countryOne.getName() + " " + countryOne.getIndicator(2015, ind6) + ", " + countryTwo.getName() + " " + countryTwo.getIndicator(2015, ind6));



                countryAdapter = new CountryAdapter(this, android.R.layout.simple_list_item_1, countryName);
                listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(countryAdapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                btn2014 = (Button) findViewById(R.id.button2014);
                btn2015 = (Button) findViewById(R.id.button2015);
                editText = (EditText) findViewById(R.id.txt_search);
                textView = (TextView) findViewById(R.id.txt_listitem1);
                checkBox = (CheckBox) findViewById(R.id.checkbox);
                nullValues= (TextView) findViewById(R.id.nullValueTv);
                nullValues.setVisibility(View.INVISIBLE);

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
               if(pieChartData.getNullValues()){
                   pieChart.setVisibility(View.INVISIBLE);
                   nullValues.setText("Unfortunately, there is no data for the exports " +
                           "of goods and services for the chosen countries.");
                   nullValues.setVisibility(View.VISIBLE);

               }
                else if(pieChartData.getNullValue()){
                   pieChart.setVisibility(View.INVISIBLE);
                   nullValues.setText("Unfortunately, there is no data for the exports " +
                           "of goods and services for " + pieChartData.getCountryThatIsNull());
                   nullValues.setVisibility(View.VISIBLE);
               }


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