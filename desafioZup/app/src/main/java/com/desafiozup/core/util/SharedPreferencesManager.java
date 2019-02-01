package com.desafiozup.core.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferencesManager {
    private Context context;
    private final String USER = "userLogin";
    private final String FILE_NAME = "user_preferences";
    private SharedPreferences sharedPref;

    public SharedPreferencesManager(Context context) {
        this.context = context;
        this.sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(String user) {
        if (!TextUtils.isEmpty(user) && sharedPref != null) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(USER, user);
            editor.apply();
        }

    }

    public String getUser() {
        return sharedPref.getString(USER, "");
    }
}