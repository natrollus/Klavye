package com.natrollus.klavye;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by byram on 9/28/15.
 */
public class Araclar {
    public static void logla(Object s){
        Log.v("bilgi",""+s);
    }
    public static void tostla(Context context,String s) {
        Toast.makeText(context, "" + s, Toast.LENGTH_LONG).show();
    }
}
