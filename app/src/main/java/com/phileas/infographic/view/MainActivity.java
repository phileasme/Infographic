package com.phileas.infographic.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.phileas.infographic.R;
import com.phileas.infographic.controller.ValueAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mSearchNFilterLv;

    private EditText mSearchEdt;

    private ArrayList<String> mStringList;

    private ValueAdapter valueAdapter;

    private TextWatcher mSearchTw;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initUI();

        initData();

        valueAdapter=new ValueAdapter(mStringList,this);

        mSearchNFilterLv.setAdapter(valueAdapter);

        mSearchEdt.addTextChangedListener(mSearchTw);


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
  
