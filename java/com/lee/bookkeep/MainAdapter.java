package com.lee.bookkeep;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lee.bookkeep.db.AccoutBean;

import java.util.Calendar;
import java.util.List;

public class MainAdapter extends BaseAdapter {
    private List<AccoutBean> mList;
    private Context mContext;
    private LayoutInflater inflater;
    private int year,month,day;

    public MainAdapter(List mList, Context context) {
        this.mList = mList;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
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
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.home_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        AccoutBean bean = mList.get(position);
        if (bean.getYear() == year && bean.getMonth() == month && bean.getDay() == day){
            viewHolder.setData(bean);
        } else {
            return null;
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView image;
        private TextView title, beizhu,price, time;
        public ViewHolder(@NonNull View view){
            image = view.findViewById(R.id.home_item_image);
            title = view.findViewById(R.id.home_item_text);
            beizhu = view.findViewById(R.id.home_item_beizhu);
            price = view.findViewById(R.id.home_item_price);
            time = view.findViewById(R.id.home_item_time);
        }

        public void setData (@NonNull AccoutBean bean){
            image.setImageResource(bean.getsImageId());
            title.setText(bean.getTypeName());
            beizhu.setText(bean.getBeizhu());
            price.setText("￥ " +bean.getPrice());
            String date = bean.getDateFormat();
            time.setText("今天 "+ date.split(" ")[1]);

        }
    }
}
