package com.lee.bookkeep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lee.bookkeep.db.AccoutBean;
import com.lee.bookkeep.db.DBManager;
import com.lee.bookkeep.search.SearchActivity;
import com.lee.bookkeep.test.TestViewActivity;
import com.lee.bookkeep.util.YusuanDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView todayLv;
    List<AccoutBean> mDatas;
    private MainAdapter adapter;
    private int year,month,day;
    private float yusuanNum;
    private float zhichu = 0 ,shouru = 0;

    private TextView header_zhichu,header_shouru,header_yusuan,header_total,header_qushi;
    private ImageView header_zhichu_con;
    private boolean zhichuVisible = true;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Lee","oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefrence = getSharedPreferences("localData",MODE_PRIVATE);
        editor = prefrence.edit();
        yusuanNum = prefrence.getFloat("yusuan",0f);

        initTime();
        initView();

        addListHeader();
    }

    private void addListHeader() {
        View header = getLayoutInflater().inflate(R.layout.home_item_top,null);
        todayLv.addHeaderView(header);

        header_zhichu = header.findViewById(R.id.home_item_zhichu);
        header_shouru = header.findViewById(R.id.home_item_shouru);
        header_yusuan = header.findViewById(R.id.home_item_yusuan);
        header_yusuan.setOnClickListener(this);
        header_total = header.findViewById(R.id.home_item_top_day);
        header_qushi = header.findViewById(R.id.home_item_qushi);
        header_qushi.setOnClickListener(this);
        header_zhichu_con = header.findViewById(R.id.home_item_btn_eye);
        header_zhichu_con.setOnClickListener(this);

    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH ) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void initView() {
        Button edit = findViewById(R.id.home_btn_edit);
        edit.setOnClickListener((View v) -> {
            startActivity(new Intent(MainActivity.this,RecordActivity.class));
        });

        ImageView iv = findViewById(R.id.home_btn_more);
        iv.setOnClickListener((View v) -> {
            startActivity(new Intent(MainActivity.this, TestViewActivity.class));
        });

        Button searchBtn = findViewById(R.id.home_btn_search);
        searchBtn.setOnClickListener(this);

        mDatas = new ArrayList<>();

        todayLv = findViewById(R.id.home_mid_layout);
        adapter = new MainAdapter(mDatas,this);
        todayLv.setAdapter(adapter);
        todayLv.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) ->{
            if (position == 0){
                return false;
            }
            int pos = position - 1;
            AccoutBean bean = mDatas.get(pos);
            int click_id = bean.getId();
            new AlertDialog.Builder(this).setTitle("提示信息").setMessage("您确定要删除这条记录吗？")
                    .setPositiveButton("确定", (DialogInterface dialog, int which) ->{
                        DBManager.removeAccountItem(click_id);
                        mDatas.remove(bean);
                        adapter.notifyDataSetChanged();
                        refreshHeader();
                        dialog.dismiss();
                    }).setNegativeButton("取消", (DialogInterface dialog, int which) ->{
                dialog.dismiss();
            }).show();
            return false;
        });

        TextView testImage = findViewById(R.id.TextTest);
        Animation utils = AnimationUtils.loadAnimation(this,R.anim.frame);
        testImage.setAnimation(utils);

    }

    @Override
    protected void onResume(){
        super.onResume();
        loadDBData();
        Log.e("Lee", "onResume");
    }

    private void loadDBData() {
        DBManager.getAccount();
        List<AccoutBean> list = DBManager.getAccountByDate(year,month,day);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
        refreshHeader();
    }

    private void refreshHeader(){
        zhichu = 0f;
        shouru = 0f;
        for (AccoutBean bean: mDatas){
            if (bean.getKindId() == 0){
                zhichu += bean.getPrice();
            } else {
                shouru += bean.getPrice();
            }
        }
        header_zhichu.setText("￥ " + zhichu);
        header_shouru.setText("本月收入￥ " + shouru);
        header_total.setText("今日支出 ￥"+zhichu+" 收入 ￥"+shouru);
        if (yusuanNum > 0){
            header_yusuan.setText("预算剩余 ￥"+(yusuanNum - zhichu));;
        } else {
            header_yusuan.setText("预算剩余 ￥"+yusuanNum);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_item_btn_eye:
                if(zhichuVisible){
                    zhichuVisible = false;
                    PasswordTransformationMethod instance = PasswordTransformationMethod.getInstance();
                    header_zhichu.setTransformationMethod(instance);
                    header_shouru.setTransformationMethod(instance);
                    header_yusuan.setTransformationMethod(instance);
                    header_zhichu_con.setImageResource(R.mipmap.ih_hide);
                } else {
                    zhichuVisible = true;
                    HideReturnsTransformationMethod hideMethod = HideReturnsTransformationMethod.getInstance();
                    header_zhichu.setTransformationMethod(hideMethod);
                    header_shouru.setTransformationMethod(hideMethod);
                    header_yusuan.setTransformationMethod(hideMethod);
                    header_zhichu_con.setImageResource(R.mipmap.ih_show);
                }
                break;
            case R.id.home_item_yusuan:
                YusuanDialog dialog = new YusuanDialog(this);
                dialog.setDialogSize();
                dialog.setOnEnsureListener((float input) ->{
                    yusuanNum = input;
                    editor.putFloat("yusuan",input);
                    header_yusuan.setText("预算剩余 ￥"+(yusuanNum - zhichu));
                });
                dialog.show();
                break;
            case R.id.home_btn_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
        }
    }

    public void onStart() {
        super.onStart();
        Log.e("Lee", "onStart");
    }
    public void onPause() {
        super.onPause();
        Log.e("Lee", "onPause");
    }
    public void onStop() {
        super.onStop();
        Log.e("Lee", "onStop");
    }
    public void onDestroy() {
        super.onDestroy();
        Log.e("Lee", "onDestroy");
    }
}