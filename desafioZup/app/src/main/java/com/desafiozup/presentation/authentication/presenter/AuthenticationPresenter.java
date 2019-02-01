package com.desafiozup.presentation.authentication.presenter;

import android.content.Context;

import com.desafiozup.core.base.BasePresenter;
import com.desafiozup.core.util.SharedPreferencesManager;
import com.desafiozup.data.authentication.model.entity.User;
import com.desafiozup.data.authentication.model.usecase.AuthenticationModelContract;
import com.desafiozup.data.authentication.model.usecase.AuthenticationUseCase;
import com.desafiozup.presentation.authentication.AuthenticationPresentationContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthenticationPresenter extends BasePresenter implements AuthenticationPresentationContract.Presenter {


    private AuthenticationPresentationContract.View view;
    private AuthenticationModelContract useCase;
    private SharedPreferencesManager preferencesManager;

    public AuthenticationPresenter(Context context, AuthenticationPresentationContract.View view) {
        this.view = view;
        useCase = new AuthenticationUseCase(context);
        preferencesManager = new SharedPreferencesManager(context);
    }

    @Override
    public void fetchUser(User user) {
        if (!useCase.isConnected()){
            view.showMessageNoConnection();
            return;
        }

        view.showLoadingLayout();
        Disposable disposable = useCase.getUserInformation(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                            view.showSuccessLayout(it);
                            preferencesManager.saveUser(user.getCpfEmail());
                        },
                        error -> view.showErrorLayout());


        if (compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }

    }

    @Override
    public String retrieveUser() {
        return preferencesManager.getUser();
    }
}
