package com.lee.bookkeep.util;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.lee.bookkeep.R;

public class KeyUtils {
    private KeyboardView keyboardView;
    private EditText edit;
    private Keyboard k1;

    public interface OnKeyDoneListener{
        void onDone();
    }
    OnKeyDoneListener keyDoneListener;
    public void setKeyDoneListener(OnKeyDoneListener listner){
        keyDoneListener = listner;
    }

    public KeyUtils(KeyboardView keyboardView, EditText edit) {
        this.keyboardView = keyboardView;
        this.edit = edit;
        this.edit.setInputType(InputType.TYPE_NULL);
        k1 = new Keyboard(this.edit.getContext(), R.xml.key);

        this.keyboardView.setKeyboard(k1);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
        }
        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = edit.getText();
            int start = edit.getSelectionStart();
            switch (primaryCode) {
                case Keyboard.KEYCODE_DELETE:
                    if(editable != null && editable.length() > 0){
                        if (start >0){
                            editable.delete(start-1,start);
                        }
                    }
                    break;
                case Keyboard.KEYCODE_CANCEL:
                    editable.clear();
                    break;
                case Keyboard.KEYCODE_DONE:
                    if (keyDoneListener != null){
                        keyDoneListener.onDone();
                    }
                    break;
                default:
                    editable.insert(start,Character.toString((char)primaryCode));
                    break;
            }
        }

        @Override
        public void onText(CharSequence text) {
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
    };

    public void showKeyboard() {
        int visible = this.keyboardView.getVisibility();
        if(visible == View.INVISIBLE || visible == View.GONE){
            this.keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visible = this.keyboardView.getVisibility();
        if(visible == View.VISIBLE){
            this.keyboardView.setVisibility(View.INVISIBLE);
        }
    }
}
