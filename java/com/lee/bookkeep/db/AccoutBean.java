package com.lee.bookkeep.db;

/*存储的一条信息*/
public class AccoutBean {
    private int id;
    private int sImageId;
    private String typeName;
    private String beizhu;
    private float price;
    private String dateFormat;
    private int year;
    private int month;
    private int day;

    public void setId(int id) {
        this.id = id;
    }

    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public int getId() {
        return id;
    }

    public int getsImageId() {
        return sImageId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public float getPrice() {
        return price;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getKindId() {
        return kindId;
    }

    private int kindId;

    public AccoutBean() {
    }

    public AccoutBean(int id, int sImageId, String typeName, String beizhu, float price, String dateFormat, int year, int month, int day, int kindId) {
        this.id = id;
        this.sImageId = sImageId;
        this.typeName = typeName;
        this.beizhu = beizhu;
        this.price = price;
        this.dateFormat = dateFormat;
        this.year = year;
        this.month = month;
        this.day = day;
        this.kindId = kindId;
    }
}
