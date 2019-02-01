package com.desafiozup.data.authentication.api;

import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.data.authentication.model.entity.User;

import io.reactivex.Observable;

public class AuthenticationRequester {

    private static LoginAPI service = AuthenticationClient.providesRetrofit().create(LoginAPI.class);
    private static AuthenticationRequester authenticationRequester;

    public static AuthenticationRequester getInstance(){
        if (authenticationRequester == null){
            authenticationRequester = new AuthenticationRequester();
        }

        return authenticationRequester;
    }

    public Observable<LoginResponse> doLogin(User user){
        return service.doLogin(user);
    }
}
