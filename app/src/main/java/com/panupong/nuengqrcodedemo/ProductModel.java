package com.panupong.nuengqrcodedemo;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {

    private int Amount;
    private String Description,NameProduct,Picture,Receive;

    public ProductModel() {

    }//Constructor Getter

    public ProductModel(int amount, String description, String nameProduct, String picture, String receive) {
        Amount = amount;
        Description = description;
        NameProduct = nameProduct;
        Picture = picture;
        Receive = receive;
    }//Constructor Setter

    protected ProductModel(Parcel in) {
        Amount = in.readInt();
        Description = in.readString();
        NameProduct = in.readString();
        Picture = in.readString();
        Receive = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getReceive() {
        return Receive;
    }

    public void setReceive(String receive) {
        Receive = receive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Amount);
        dest.writeString(Description);
        dest.writeString(NameProduct);
        dest.writeString(Picture);
        dest.writeString(Receive);
    }
}
