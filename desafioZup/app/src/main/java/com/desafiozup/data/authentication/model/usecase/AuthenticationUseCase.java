package com.desafiozup.data.authentication.model.usecase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.desafiozup.data.authentication.api.AuthenticationRequester;
import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.data.authentication.model.entity.User;

import io.reactivex.Observable;

public class AuthenticationUseCase implements AuthenticationModelContract{

    private Context context;

    public AuthenticationUseCase(Context context) {
        this.context = context;
    }

    @Override
    public Observable<LoginResponse> getUserInformation(User user) {
        return AuthenticationRequester.getInstance().doLogin(user);
    }

    @Override
    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
