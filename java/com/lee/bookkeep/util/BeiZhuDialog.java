package com.lee.bookkeep.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.lee.bookkeep.R;

public class BeiZhuDialog extends Dialog implements View.OnClickListener{
    private EditText et;
    private OnEnsureListener ensureListener;

    public void setOnEnsureListener(OnEnsureListener listener){
        ensureListener = listener;
    }

    public BeiZhuDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.dialog_beizhu);

        et = findViewById(R.id.dialog_beizhu_et);

        Button ensure = findViewById(R.id.dialog_beizhu_ensure);
        ensure.setOnClickListener(this);

        Button cancel = findViewById(R.id.dialog_beizhu_cancel);
        cancel.setOnClickListener(this);
    }

    public interface OnEnsureListener{
        void onEnsure(String text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_beizhu_ensure:
                if (ensureListener != null){
                    String text = et.getText().toString().trim();
                    if (!StringUtil.isEmpty(text)){
                        ensureListener.onEnsure(text);
                    }
                }
                dismiss();
                break;
            case R.id.dialog_beizhu_cancel:
                cancel();
                break;
        }
    }

    public void setDialogSize() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        Display display = window.getWindowManager().getDefaultDisplay();
        lp.width = display.getWidth();
        lp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(lp);
        handler.sendEmptyMessageDelayed(1,100);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
            et.setFocusable(true);
        }
    };
}
