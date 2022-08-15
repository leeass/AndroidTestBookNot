package com.lee.bookkeep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.lee.bookkeep.record_frag.InOutComeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private ViewPager vp;
    private TabLayout tl;
    private List<Fragment> fragList = new ArrayList<>();
    private List<TabLayout.Tab> tabList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Log.e("Lee", "RecordActivity    onCreate");
        
        initView();
    }

    private void initView() {
        ImageView close = findViewById(R.id.record_btn_close);
        close.setOnClickListener((View v) -> {
            finish();
        });

        vp = findViewById(R.id.record_vp);
        tl = findViewById(R.id.record_tablayout);

        fragList.add(InOutComeFragment.newInstance("支出",0));
        fragList.add(InOutComeFragment.newInstance("收入",1));
        RecordPageAdapter adapter = new RecordPageAdapter(getSupportFragmentManager(),fragList);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
    }


    public void onStart() {
        super.onStart();
        Log.e("Lee", "RecordActivity    onStart");
    }
    public void onResume() {
        super.onResume();
        Log.e("Lee", "RecordActivity    onResume");
    }
    public void onPause() {
        super.onPause();
        Log.e("Lee", "RecordActivity    onPause");
    }
    public void onStop() {
        super.onStop();
        Log.e("Lee", "RecordActivity    onStop");
    }
    public void onDestroy() {
        super.onDestroy();
        Log.e("Lee", "RecordActivity    onDestroy");
    }
}