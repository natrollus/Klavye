package com.natrollus.klavye;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodSubtype;

import java.util.HashMap;
import java.util.List;

import static com.natrollus.klavye.Araclar.logla;

public class Klavye extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    KeyboardView klavyeView;
    Keyboard klavye;
    InputConnection ic;

    boolean ctrl = false;
    boolean alt = false;
    boolean shift = false;


    @Override
    public View onCreateInputView() {
        klavyeView = (KeyboardView) getLayoutInflater().inflate(R.layout.klavye,null);
        klavye = new Keyboard(getApplicationContext(),R.xml.tuslar);
        klavyeView.setKeyboard(klavye);
        klavyeView.setVisibility(View.VISIBLE);
        klavyeView.setPreviewEnabled(false);
        klavyeView.setOnKeyboardActionListener(this);
        return klavyeView;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        ic = getCurrentInputConnection();
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_DONE:
                tusBasCek(KeyEvent.KEYCODE_ENTER);
                break;
            case -KeyEvent.KEYCODE_ESCAPE:
                tusBasCek(KeyEvent.KEYCODE_ESCAPE);
                break;
            case -KeyEvent.KEYCODE_TAB:
                tusBasCek(KeyEvent.KEYCODE_TAB);
                break;
            case -KeyEvent.KEYCODE_DPAD_UP:
                tusBasCek(KeyEvent.KEYCODE_DPAD_UP);
                break;
            case -KeyEvent.KEYCODE_DPAD_DOWN:
                tusBasCek(KeyEvent.KEYCODE_DPAD_DOWN);
                break;
            case -KeyEvent.KEYCODE_DPAD_LEFT:
                tusBasCek(KeyEvent.KEYCODE_DPAD_LEFT);
                break;
            case -KeyEvent.KEYCODE_DPAD_RIGHT:
                tusBasCek(KeyEvent.KEYCODE_DPAD_RIGHT);
                break;
            case Keyboard.KEYCODE_SHIFT:
                shift = !shift;
                harfleriBuyut(shift);
                break;
            default:
                char kar = (char) primaryCode;
                if (ctrl) {
                    logla(primaryCode);
                    kontroltusu(primaryCode);
                } else {
                    String yaz = String.valueOf(kar);
                    if (shift){
                        yaz = yaz.toUpperCase();
                        shift=!shift;
                        harfleriBuyut(shift);
                    }
                    ic.commitText(yaz,1);
                }
                break;
        }
    }

    private void harfleriBuyut(boolean shift) {
        List<Keyboard.Key> keys = klavye.getKeys();
        for (Keyboard.Key key:keys){
            String etiket = String.valueOf((char)key.codes[0]);
            if (etiket.matches("[a-zA-Z]")){
                if (shift){
                    key.label = etiket.toUpperCase();
                } else {
                    key.label = etiket.toLowerCase();
                }
                klavyeView.invalidateAllKeys();
            }
        }
    }


    public void kontroltusu(int primaryCode){

        int ctrlKeyKod = KeyEvent.KEYCODE_CTRL_LEFT;
        int digerKeyKod = KeyEvent.keyCodeFromString("KEYCODE_"+(char)primaryCode);

        tusBasCek(ctrlKeyKod, digerKeyKod);

        ctrl = !ctrl;
    }

    public void tusBasCek(int... kodlar) {
        int j=0;
        KeyEvent[] keyEvent = new KeyEvent[kodlar.length];
        for (int t:kodlar){
            keyEvent[j] = new KeyEvent(KeyEvent.ACTION_DOWN,t);
            ic.sendKeyEvent(keyEvent[j]);
            j++;
        }
        j=0;
        for (int t:kodlar){
            keyEvent[j] = new KeyEvent(KeyEvent.ACTION_UP,t);
            ic.sendKeyEvent(keyEvent[j]);
            j++;
        }
    }


    @Override
    public void onPress(int primaryCode) {
        ic = getCurrentInputConnection();
        if (primaryCode==-KeyEvent.KEYCODE_CTRL_LEFT){
            ctrl = !ctrl;
        }
    }

    @Override
    public void onRelease(int primaryCode) {
    }
    @Override
    public void onText(CharSequence text) {
        String yazi = text.toString();
        if (yazi.equals("Esc")) {
            hideWindow();
        }
    }
    @Override
    public void swipeLeft() {

    }
    @Override
    public void swipeRight() {

    }
    @Override
    public void swipeDown() {

    }
    @Override
    public void swipeUp() {

    }

    private HashMap<Integer, Keyboard.Key> tuslariHazirla() {
        HashMap<Integer,Keyboard.Key> tamliste = new HashMap<>();
        for (Keyboard.Key k : klavye.getKeys()) {
            tamliste.put(k.codes[0],k);
        }
        return tamliste;
    }
}
