package com.lee.bookkeep.test;

import android.os.Parcel;
import android.os.Parcelable;

public class SerializeDemo2 implements Parcelable {
    private int id;

    private String name;
    private int age;

    public SerializeDemo2(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    protected SerializeDemo2(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<SerializeDemo2> CREATOR = new Creator<SerializeDemo2>() {
        @Override
        public SerializeDemo2 createFromParcel(Parcel in) {
            return new SerializeDemo2(in);
        }

        @Override
        public SerializeDemo2[] newArray(int size) {
            return new SerializeDemo2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(age);
    }
}
