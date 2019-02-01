package com.desafiozup.data.authentication.model.entity;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user")
    private String cpfEmail;

    @SerializedName("password")
    private String password;

    public String getCpfEmail() {
        return cpfEmail;
    }

    public void setCpfEmail(String cpfEmail) {
        this.cpfEmail = cpfEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

