package com.desafiozup.presentation.authentication.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.desafiozup.R;
import com.desafiozup.core.base.BaseActivity;
import com.desafiozup.core.util.FieldsValidator;
import com.desafiozup.core.util.SingleToast;
import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.data.authentication.model.entity.User;
import com.desafiozup.presentation.authentication.AuthenticationPresentationContract;
import com.desafiozup.presentation.authentication.presenter.AuthenticationPresenter;
import com.desafiozup.presentation.home.ui.HomeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthenticationActivity extends BaseActivity<AuthenticationPresentationContract.Presenter> implements AuthenticationPresentationContract.View {


    @BindView(R.id.et_user)
    EditText userEditText;

    @BindView(R.id.include_login_error_layout)
    ViewGroup errorLayout;

    @BindView(R.id.et_password)
    EditText passwordEditText;

    @BindView(R.id.pb_login)
    ProgressBar loadingProgressBar;

    @BindView(R.id.btn_login)
    Button loginButton;

    @BindView(R.id.gr_login_loading)
    Group loadingGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userEditText.requestFocus();
        userNameOnClickKeyboardEnterListener();
        passwordOnClickKeyboardEnterListener();

    }

    private void passwordOnClickKeyboardEnterListener() {
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE)) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(passwordEditText.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                loginButton.performClick();
            }
            return false;
        });
    }

    private void userNameOnClickKeyboardEnterListener() {
        userEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(userEditText.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
            return false;
        });
    }

    @OnClick(R.id.btn_login)
    void onClickLogin() {
        User user = new User();
        user.setCpfEmail(userEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        if (FieldsValidator.isValidPassword(user.getPassword()) && FieldsValidator.isValidLogin(user.getCpfEmail())) {
            presenter.fetchUser(user);
        } else {
            showInvalidUserPassword();
        }
    }

    @Override
    public void showInvalidUserPassword() {
        SingleToast.show(this, getString(R.string.invalid_user_password), Toast.LENGTH_SHORT);
    }

    @Override
    public int getLayout() {
        return R.layout.login_activity;
    }


    @Override
    public void showErrorLayout() {
        errorLayout.setVisibility(View.VISIBLE);
        loadingProgressBar.setVisibility(View.GONE);
        loadingGroup.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessLayout(LoginResponse loginResponse) {
        errorLayout.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("login", loginResponse);
        overridePendingTransition(R.anim.activity_transition_fade_in,R.anim.activity_transition_fade_out);
        startActivity(intent);
    }

    @Override
    public void showLoadingLayout() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        loadingProgressBar.animate();
        errorLayout.setVisibility(View.GONE);
        loadingGroup.setVisibility(View.GONE);
    }

    @Override
    public void showMessageNoConnection() {
        SingleToast.show(this, "Por favor conecte-se a Internet ou tente novamente mais tarde", Toast.LENGTH_LONG);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadingGroup.setVisibility(View.VISIBLE);
        clearPassword();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!TextUtils.isEmpty(presenter.retrieveUser())) {
            userEditText.setText(presenter.retrieveUser());
            passwordEditText.requestFocus();
        }
    }

    private void clearPassword() {
        passwordEditText.setText("");
    }

    @Override
    public AuthenticationPresentationContract.Presenter createPresenter() {
        return new AuthenticationPresenter(this, this);
    }
}
