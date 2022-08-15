package com.lee.bookkeep.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lee.bookkeep.R;

public class DateDialog extends Dialog implements View.OnClickListener {
    private EditText hourEdit,minuteEdit;
    private Button btn_sure,btn_cancel;
    private DatePicker dp;

    private DateDialog.OnEnsureListener ensureListener;

    public interface OnEnsureListener{
        void onEnsure(String dateStr,int year,int month,int day);
    }

    public void setOnEnsureListener(DateDialog.OnEnsureListener listener){
        ensureListener = listener;
    }

    public DateDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.dialog_date);
        initView();
        hideDatePickerHeader();
    }

    private void initView() {
        dp = findViewById(R.id.dialog_date_dp);
        hourEdit = findViewById(R.id.dialog_date_hour);
        minuteEdit = findViewById(R.id.dialog_date_minute);

        btn_sure = findViewById(R.id.dialog_date_ensure);
        btn_sure.setOnClickListener(this);
        btn_cancel = findViewById(R.id.dialog_date_cancel);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_date_ensure:
                int year = dp.getYear();
                int month = dp.getMonth()+1;
                String monthStr = String.valueOf(month);
                if (month < 10){
                    monthStr = "0"+monthStr;
                }
                int day = dp.getDayOfMonth();
                String dayStr = String.valueOf(day);
                if (day < 10){
                    dayStr = "0"+dayStr;
                }
                int hour = 0;
                String hourStr = hourEdit.getText().toString();
                if(!TextUtils.isEmpty(hourStr)){
                    hour = Integer.parseInt(hourStr);
                    hour = hour%24;
                    hourStr = String.valueOf(hour);
                } else {
                    hourStr = "0";
                }
                if (hour < 10){
                    hourStr = "0"+hourStr;
                }
                int minute = 0;
                String minuteStr = minuteEdit.getText().toString();
                if (!TextUtils.isEmpty(minuteStr)){
                    minute = Integer.parseInt(minuteStr);
                    minute = minute%60;
                    minuteStr = String.valueOf(minute);
                } else {
                    minuteStr = "0";
                }
                if (minute < 10){
                    minuteStr = "0"+minuteStr;
                }
                String dateFormat = year + "年" + month + "月"+ day +"日 " + hourStr +":"+ minuteStr;
                if (ensureListener != null){
                    ensureListener.onEnsure(dateFormat,year,month,day);
                }
                cancel();
                break;
            case R.id.dialog_date_cancel:
                cancel();
                break;
        }
    }

    private void hideDatePickerHeader(){
        ViewGroup viewRoot = (ViewGroup) dp.getChildAt(0);
        if (viewRoot == null){
            return;
        }
        View header = viewRoot.getChildAt(0);
        if (header == null){
            return;
        }
        int headId = getContext().getResources().getIdentifier("day_picker_selector_layout","id","android");
        if (headId == header.getId()) {
            header.setVisibility(View.GONE);
        }
        headId = getContext().getResources().getIdentifier("date_picker_header","id","android");
        if (headId == header.getId()) {
            header.setVisibility(View.GONE);
        }
    }
}
