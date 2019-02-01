package com.desafiozup.data.authentication.model.usecase;

import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.data.authentication.model.entity.User;

import io.reactivex.Observable;

public interface AuthenticationModelContract {

    Observable<LoginResponse> getUserInformation(User user);

    boolean isConnected();
}
