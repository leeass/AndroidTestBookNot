package com.lee.bookkeep.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.bookkeep.MainAdapter;
import com.lee.bookkeep.R;
import com.lee.bookkeep.db.AccoutBean;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private List<AccoutBean> myList = new ArrayList<>();
    private MainAdapter adapter;
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
    }

    private void initView() {
        adapter = new MainAdapter(myList,this);
        ListView lv = findViewById(R.id.search_list);
        lv.setAdapter(adapter);

        ImageView back = findViewById(R.id.search_back);
        back.setOnClickListener(this);
        ImageView search = findViewById(R.id.search_done);
        search.setOnClickListener(this);
        edit = findViewById(R.id.search_edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_back:
                finish();
                break;
            case R.id.search_done:
                String searchStr = edit.getText().toString();
                if(!TextUtils.isEmpty(searchStr)){

                } else {
                    Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}