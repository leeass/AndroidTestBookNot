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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.lee.bookkeep.R;

public class YusuanDialog extends Dialog implements View.OnClickListener{
    private EditText et;
    private OnEnsureListener ensureListener;

    public void setOnEnsureListener(OnEnsureListener listener){
        ensureListener = listener;
    }

    public YusuanDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.dialog_yusuan);

        et = findViewById(R.id.dialog_yusuan_edit);

        Button ensure = findViewById(R.id.dialog_yusuan_ensure);
        ensure.setOnClickListener(this);

        Button cancel = findViewById(R.id.dialog_yusuan_cancel);
        cancel.setOnClickListener(this);
    }

    public interface OnEnsureListener{
        void onEnsure(float num);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_yusuan_ensure:
                if (ensureListener != null){
                    String text = et.getText().toString().trim();
                    if (!StringUtil.isEmpty(text)){
                        float yusuan = Float.parseFloat(text);
                        if(yusuan <= 0f){
                            Toast.makeText(getContext(),"输入数据不能小于0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ensureListener.onEnsure(yusuan);
                    } else {
                        Toast.makeText(getContext(),"输入数据不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
                dismiss();
                break;
            case R.id.dialog_yusuan_cancel:
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
