package com.natrollus.klavye;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import android.graphics.*;

public class Araclar {
    public static void logla(Object s){
        Log.v("bilgi",""+s);
    }
    public void toast(Context context,String s) {
        Toast.makeText(context,""+s,Toast.LENGTH_LONG).show();
    }
	public float uzaklik(Point a,Point b){
		float x = a.x - b.x;
		float y = a.y - b.y;
		return (float) Math.sqrt(x*x+y*y);
	}
}
