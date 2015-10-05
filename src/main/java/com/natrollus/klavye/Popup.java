package com.natrollus.klavye;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;

import static com.natrollus.klavye.Araclar.logla;

/**
 * Created by byram on 9/29/15.
 */
public class Popup extends Keyboard {
    public Popup(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
        logla("conss..");
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y, XmlResourceParser parser) {
        logla("burda..");
        return super.createKeyFromXml(res, parent, x, y, parser);
    }
}
