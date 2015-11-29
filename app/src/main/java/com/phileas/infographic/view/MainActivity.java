package com.phileas.infographic.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.phileas.infographic.R;
import com.phileas.infographic.controller.ReadFromJson;
import com.phileas.infographic.controller.ValueAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       ReadFromJson readj = new ReadFromJson("IC.BUS.EASE.XQ.json",this.getApplicationContext());

            readj.loadJSONFromAsset();

    }


}
