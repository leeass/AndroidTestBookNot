package com.lee.bookkeep.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static SQLiteDatabase db;
    public static void initDB(Context context){
        DBOpenHelper helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();

        StringBuilder sb1 = new StringBuilder();
    }

    public static List<TypeBean> getListByType(int kind){
        List<TypeBean> list = new ArrayList<>();
        String sql = "select * from typetb where kind = "+kind;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            String typeName = cursor.getString(cursor.getColumnIndex("typeName"));
            int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            int sImageId = cursor.getInt(cursor.getColumnIndex("sImageId"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            TypeBean bean = new TypeBean(id,typeName,imageId,sImageId,kind);
            list.add(bean);
        }
        return list;
    }

    public static void insertToAccount(AccoutBean bean){
        ContentValues values = new ContentValues();
        values.put("typeName",bean.getTypeName());
        values.put("sImageId",bean.getsImageId());
        values.put("price",bean.getPrice());
        values.put("dateFormat",bean.getDateFormat());
        values.put("beizhu",bean.getBeizhu());
        values.put("year",bean.getYear());
        values.put("month",bean.getMonth());
        values.put("day",bean.getDay());
        values.put("kindId",bean.getKindId());

        db.insert("accounttb", null,values);
    }

    public static List<AccoutBean> getAccount(){
        List<AccoutBean> accountList = new ArrayList<>();
        String sql = "select * from accounttb";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int sImageId = cursor.getInt(cursor.getColumnIndex("sImageId"));
            String typename = cursor.getString(cursor.getColumnIndex("typeName"));
            String beizhu = cursor.getString(cursor.getColumnIndex("beizhu"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));
            String dateFormat = cursor.getString(cursor.getColumnIndex("dateFormat"));
            int year = cursor.getInt(cursor.getColumnIndex("year"));
            int month = cursor.getInt(cursor.getColumnIndex("month"));
            int day = cursor.getInt(cursor.getColumnIndex("day"));
            int kind = cursor.getInt(cursor.getColumnIndex("kindId"));

            AccoutBean bean = new AccoutBean(id,sImageId,typename,beizhu,price,dateFormat,year,month,day,kind);
            accountList.add(bean);
        }
        return accountList;
    }

    public static List<AccoutBean>  getAccountByDate(int year,int month,int day){
        List<AccoutBean> accountList = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? and day=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "",month+"",day+""});
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int sImageId = cursor.getInt(cursor.getColumnIndex("sImageId"));
            String typename = cursor.getString(cursor.getColumnIndex("typeName"));
            String beizhu = cursor.getString(cursor.getColumnIndex("beizhu"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));
            String dateFormat = cursor.getString(cursor.getColumnIndex("dateFormat"));
            int year1 = cursor.getInt(cursor.getColumnIndex("year"));
            int month1 = cursor.getInt(cursor.getColumnIndex("month"));
            int day1 = cursor.getInt(cursor.getColumnIndex("day"));
            int kind = cursor.getInt(cursor.getColumnIndex("kindId"));

            AccoutBean bean = new AccoutBean(id,sImageId,typename,beizhu,price,dateFormat,year1,month1,day1,kind);
            accountList.add(bean);
        }
        return accountList;
    }

    public static int removeAccountItem(int id){
        int i = db.delete("accounttb","id=?",new String[]{id+""});
        return i;
    }
}
