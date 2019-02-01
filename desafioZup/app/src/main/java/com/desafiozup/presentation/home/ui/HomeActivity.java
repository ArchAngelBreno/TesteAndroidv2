package com.desafiozup.presentation.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.desafiozup.R;
import com.desafiozup.core.base.BaseActivity;
import com.desafiozup.core.base.BaseContract;
import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.presentation.home.ui.fragment.StatementFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.tv_user_name)
    TextView userName;

    @BindView(R.id.tv_account_number)
    TextView accountNumber;

    @BindView(R.id.tv_balance)
    TextView totalBalance;

    @BindView(R.id.srl_refresh_home)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment();
        populateUserInformation();
        doRefresh();
    }


    private void populateUserInformation(){
        LoginResponse response = getIntent().getParcelableExtra("login");
        userName.setText(response.getUserAccount().getName());
        accountNumber.setText(String.format("%s / %s", response.getUserAccount().getBankAccount(), response.getUserAccount().getAgency()));
        totalBalance.setText(String.format("R$ %s",String.valueOf(response.getUserAccount().getBalance())));
    }

    @Override
    public int getLayout() {
        return R.layout.home_activity;
    }


    //TODO: REMOVER PRESENTER NESTA ACTIVITY
    @Override
    public BaseContract.Presenter createPresenter() {
        return null;
    }


    private void loadFragment(){
        StatementFragment fragment = new StatementFragment();
        FragmentManager fm  = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(fragment.getTag()).replace(R.id.fl_home_container, fragment, fragment.getTag()).commitAllowingStateLoss();
    }

    private void doRefresh(){
        swipeRefresh.setOnRefreshListener(() -> {
            loadFragment();
            swipeRefresh.setRefreshing(false);
        });
    }

    @OnClick(R.id.iv_logout)
    void onClickLogout(){
        finish();
    }
}
