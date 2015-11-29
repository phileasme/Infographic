package com.phileas.infographic.controller;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Peace on 29/11/2015.
 */
public class ReadAllAssets {


public ReadAllAssets(Context context){
    ReadAllAssetFiles("",context);
}

    /**
     *
     * @param path
     * @param myContext
     * @return
     */
    private boolean ReadAllAssetFiles(String path,Context myContext) {

        String [] list;
        try {
            list = myContext.getAssets().list(path);
            if (list.length > 0) {
                for (String file : list) {
                    if (!ReadAllAssetFiles((path + "/" + file), myContext)){
                        return false;}
                    else{
                        ReadFromJson readCurrentFile = new ReadFromJson(file,myContext);
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

}
