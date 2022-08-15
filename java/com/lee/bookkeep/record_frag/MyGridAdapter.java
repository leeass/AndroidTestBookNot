package com.lee.bookkeep.record_frag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lee.bookkeep.R;
import com.lee.bookkeep.db.TypeBean;

import java.util.List;

public class MyGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<TypeBean> mList;

    public int getSelectIndex() {
        return selectIndex;
    }

    private int selectIndex = 0;
    public MyGridAdapter(Context context,List<TypeBean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.frag_grid_item,null);
        TypeBean bean = mList.get(position);
        ImageView image = convertView.findViewById(R.id.frag_grid_item_image);
        if(selectIndex == position){
            image.setImageResource(bean.getsImageId());
        } else {
            image.setImageResource(bean.getImageId());
        }
        TextView text = convertView.findViewById(R.id.frag_grid_item_text);
        text.setText(bean.getTypeName());
        return convertView;
    }

    public void changeSelect(int index) {
        if(index != selectIndex){
            selectIndex = index;
            notifyDataSetChanged();
        }
    }
}
