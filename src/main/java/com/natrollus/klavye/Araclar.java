package com.natrollus.klavye;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Araclar {
    public static void logla(Object s){
        Log.v("bilgi",""+s);
    }
    public void toast(Context context,String s) {
        Toast.makeText(context,""+s,Toast.LENGTH_LONG).show();
    }
}