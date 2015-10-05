package com.natrollus.klavye;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.List;

import static com.natrollus.klavye.Araclar.logla;

public class KlavyeView extends KeyboardView {

    Paint p = new Paint();
    Canvas canvas;
    int boyut = 0;
    boolean shift = false;
    Context context;



    public KlavyeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected boolean onLongPress(Key popupKey) {
        if (popupKey.popupCharacters!=null) {
            String tus = popupKey.label.toString();
            if (tus.matches("[0-9a-zA-Zİ]")){
                getOnKeyboardActionListener().onKey(popupKey.popupCharacters.charAt(0),null);
                return true;
            } else if (tus.matches("[\"-./,]")){
                getOnKeyboardActionListener().onKey(popupKey.popupCharacters.charAt(0), null);
                return true;
            } else if (tus.equals("Esc")){
                getOnKeyboardActionListener().onText("Esc");
                return true;
            }
        }
        return false;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        shift = getKeyboard().isShifted();
        logla("shift:"+shift);
        List<Key> keys = getKeyboard().getKeys();

        float density = getResources().getDisplayMetrics().density;

        boyut = (int)(density*10);
        p.setTextAlign(Paint.Align.CENTER);
        logla("den:" + density);
        p.setTextSize(boyut);
        p.setAntiAlias(true);
        p.setColor(Color.LTGRAY);
        int h = 0,i = 0;
        String[] ust_sayi = {"=","!","^","#","+","%","&","(",")","*"};
        String[] ust_harf = {"@","é","ü","ı","ö","ş","ğ","[","]","<",">","ç","{","}"};
        String[] ust_isaret = {"'","?",":","\\",";"};
        for (Key key:keys) {
            String etiket = String.valueOf((char)key.codes[0]);
            if (key.label!=null) {
                if (etiket.matches("[0-9]")) {
                    ciz(key,ust_sayi[Integer.valueOf(etiket)]);
                } else if (etiket.matches("[a-z]")){

                    if (etiket.matches("[qeuiosgklzxcnm]")){
                        ciz(key,ust_harf[h]);
                        h++;
                    }

                } else if (etiket.matches("[\"-./,]")){
                    ciz(key,ust_isaret[i]);
                    i++;
                }
            }
        }
    }

    private void ciz (Key key,String etiket) {
        canvas.drawText(etiket, key.x + (key.width / 2) + boyut - 10, key.y + boyut + 5, p);
    }

}
