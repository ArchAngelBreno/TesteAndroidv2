package com.desafiozup.data.authentication.api;

import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.data.authentication.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("login")
    Observable<LoginResponse> doLogin(@Body User user);

}
