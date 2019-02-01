package com.desafiozup.data.authentication.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserAccount implements Parcelable {

    @SerializedName("userId")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("bankAccount")
    private String bankAccount;

    @SerializedName("agency")
    private String agency;

    @SerializedName("balance")
    private double balance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.bankAccount);
        dest.writeString(this.agency);
        dest.writeDouble(this.balance);
    }

    public UserAccount() {
    }

    protected UserAccount(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.bankAccount = in.readString();
        this.agency = in.readString();
        this.balance = in.readDouble();
    }

    public static final Parcelable.Creator<UserAccount> CREATOR = new Parcelable.Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel source) {
            return new UserAccount(source);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };
}
