package com.desafiozup.presentation.authentication;

import com.desafiozup.core.base.BaseContract;
import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.data.authentication.model.entity.User;

public interface AuthenticationPresentationContract {

    interface View extends BaseContract.View<AuthenticationPresentationContract.Presenter>{
        void showErrorLayout();
        void showSuccessLayout(LoginResponse loginResponse);
        void showInvalidUserPassword();
        void showLoadingLayout();
        void showMessageNoConnection();
    }

    interface Presenter extends BaseContract.Presenter{
        void fetchUser(User user);
        String retrieveUser();
    }
}
