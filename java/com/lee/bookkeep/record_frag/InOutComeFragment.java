package com.lee.bookkeep.record_frag;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.bookkeep.R;
import com.lee.bookkeep.db.AccoutBean;
import com.lee.bookkeep.db.DBManager;
import com.lee.bookkeep.db.TypeBean;
import com.lee.bookkeep.util.BeiZhuDialog;
import com.lee.bookkeep.util.DateDialog;
import com.lee.bookkeep.util.KeyUtils;
import com.lee.bookkeep.util.MyDateFormat;
import com.lee.bookkeep.util.StringUtil;

import java.util.Calendar;
import java.util.List;

public class InOutComeFragment extends Fragment {
    private EditText myEdit;
    private TextView myText,myBeizhu,myDate;
    private ImageView MyImage;
    private GridView myGrid;
    private MyGridAdapter adapter;

    private String name;
    private int kindId;
    private AccoutBean accout = new AccoutBean(); //保存的记录
    private  List<TypeBean> myBeanList;

    public InOutComeFragment() {

    }

    public static InOutComeFragment newInstance(String name, int kindId) {
        InOutComeFragment fragment = new InOutComeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putInt("kindId",kindId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle argBundle = getArguments();
        name = argBundle.getString("name");
        kindId = argBundle.getInt("kindId");
        View view = inflater.inflate(R.layout.fragment_out_come, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        KeyboardView kbv =  view.findViewById(R.id.frag_record_keyboard);
        myEdit = view.findViewById(R.id.frag_record_oc_edit);
        new KeyUtils(kbv,myEdit).setKeyDoneListener(() -> {
            String jiage = myEdit.getText().toString();
            if (!StringUtil.isEmpty(jiage)){
                TypeBean bean = myBeanList.get(adapter.getSelectIndex());
                accout.setBeizhu(myBeizhu.getText().toString());
                accout.setsImageId(bean.getsImageId());
                accout.setKindId(kindId);
                accout.setTypeName(bean.getTypeName());
                accout.setDateFormat(myDate.getText().toString());
                accout.setPrice(Float.valueOf(jiage));

                Log.e("Lee", " yaer  === "+ accout.getYear() + "month == "+ accout.getMonth() + "day ="+accout.getDay());

                DBManager.insertToAccount(accout);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), "没有写入钱数", Toast.LENGTH_SHORT).show();
            }

        });

        myText = view.findViewById(R.id.frag_record_oc_text);
        MyImage = view.findViewById(R.id.frag_record_oc_image);
        myBeizhu = view.findViewById(R.id.frag_record_beizhu);
        myDate = view.findViewById(R.id.frag_record_date);
        String dateFormate = MyDateFormat.getRecordTime();
        myDate.setText(dateFormate);
        accout.setDateFormat(dateFormate);
        Calendar cal = Calendar.getInstance();
        accout.setYear(cal.get(cal.YEAR));
        accout.setMonth(cal.get(cal.MONTH)+1);
        accout.setDay(cal.get(cal.DAY_OF_MONTH));

        myBeanList = DBManager.getListByType(kindId);
        myGrid = view.findViewById(R.id.frag_record_grid);
        adapter = new MyGridAdapter(this.getActivity(),myBeanList);
        myGrid.setAdapter(adapter);

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelect(position);
                TypeBean bean = myBeanList.get(position);
                myText.setText(bean.getTypeName());
                MyImage.setImageResource(bean.getsImageId());
            }
        });

        myBeizhu.setOnClickListener((View v) -> {
            BeiZhuDialog bzDialog = new BeiZhuDialog(getActivity());
            bzDialog.show();
            bzDialog.setDialogSize();
            bzDialog.setOnEnsureListener((String inputStr) -> {
                myBeizhu.setText(inputStr);
            });
        });

        myDate.setOnClickListener((View v) -> {
            DateDialog dialog = new DateDialog(getContext());
            dialog.show();
            dialog.setOnEnsureListener((String dateStr,int year,int month,int day) -> {
                myDate.setText(dateStr);
                accout.setDateFormat(dateStr);
                accout.setYear(year);
                accout.setMonth(month);
                accout.setDay(day);
            });
        });
    }
}