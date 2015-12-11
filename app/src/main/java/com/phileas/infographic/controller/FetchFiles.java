package com.phileas.infographic.controller;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Peace on 03/12/2015.
 */
public class FetchFiles {
    String file;
    Context context;
    String [] listOfIndicators;
    ArrayList<String> indicatorNames;
    String [] urls;
    //Country,      Indicator         Value
    HashMap<String,HashMap<Pair<Integer,String>,String>> allCountriesInfoExtract;
    /**
     * @param context;
     */
    public FetchFiles(Context context){
        allCountriesInfoExtract = new HashMap();
        this.context = context;
        getListOfIndicatorNames();
        int sizeOfArrayOfIndicators = indicatorNames.size();
        urls =new  String[sizeOfArrayOfIndicators];
        int i = 0;
        for (String indicitator: indicatorNames){
            String tempfile = "http://api.worldbank.org/countries/all/indicators/"+indicitator+"?format=json&per_page=4000&date=2014:2015";
            urls[i] = tempfile;
            DownloadFilesTask fileToDownload = new DownloadFilesTask();
            fileToDownload.setIndicatorName(indicitator);
            fileToDownload.execute(urls[i]);
            i++;
        }
    }

    private void getListOfIndicatorNames() {

        try {

            String[] list;
            indicatorNames = new ArrayList();
            list = context.getAssets().list("");
            for (String file : list) {
                int i = file.lastIndexOf('.');
                String extension = "";
                if (i > 0) {
                    extension = file.substring(i + 1);
                    if (extension.equals("json")) {
                        indicatorNames.add(file.substring(0, i));
//                        Log.i("filesIndicName", file.substring(0, i));
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private class DownloadFilesTask extends AsyncTask<String, Integer,JSONArray> {
        String indicatorName;
        protected JSONArray doInBackground(String... urls) {

            if (urls.length != 1) return null;
            int count = urls.length;
            long totalSize = 0;
            try {
                InputStream inputStream = (new URL(urls[0])).openStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                byte[] bufferStream = new byte[1024];
                int bufferLength;

                ByteArrayOutputStream outputArray = new ByteArrayOutputStream();
                while((bufferLength = dataInputStream.read(bufferStream))>0){
                    outputArray.write(bufferStream ,0,bufferLength);
                }

                publishProgress((int) ((0 / (float) count) * 100));


                return new JSONArray(outputArray.toString("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }catch (Exception e){
                Log.i("Ow no ","what is it again");
                return null;
            }
        }

        /**
         * Treating the fetched Data of the JSON file
         * @param result
         */
        protected void onPostExecute(JSONArray result) {
            String resultString = result.toString();
            ReadFromJson readcurrentJson = new ReadFromJson(indicatorName,context);
            //Country,      Indicator         Value
            HashMap<String,HashMap<Pair<Integer,String>,String>> contentExtracted=  readcurrentJson.extractingJsonInfo(resultString);
            for(String a : contentExtracted.keySet()){
                if(!allCountriesInfoExtract.containsKey(a)){
                    allCountriesInfoExtract.putAll(contentExtracted);
                }else{
                    for(Pair<Integer,String> b : contentExtracted.get(a).keySet()) {
                        allCountriesInfoExtract.get(a).putAll(contentExtracted.get(a));
                    }
                }
            }

        }
        protected void setIndicatorName(String name){
            indicatorName = name;
        }
    }

    public  HashMap<String,HashMap<Pair<Integer,String>,String>>  updateCountriesInfo(){
        return allCountriesInfoExtract;
    }

}