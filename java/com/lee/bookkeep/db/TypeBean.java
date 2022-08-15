package com.lee.bookkeep.db;

public class TypeBean {
    private int id;
    private String typeName;
    private int imageId;
    private int sImageId;
    private int kind;

    public TypeBean(int cId,String cTypeName,int cImageID,int cSImageId,int cKind){
        id = cId;
        typeName = cTypeName;
        imageId = cImageID;
        sImageId = cSImageId;
        kind = cKind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getsImageId() {
        return sImageId;
    }

    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
