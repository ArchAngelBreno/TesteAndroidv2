package com.desafiozup.core.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View<P> {

    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initButterKnife();
        presenter = createPresenter();
    }

    public abstract @LayoutRes
    int getLayout();

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter!= null ){
            presenter.onAttach();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter!=null){
            presenter.onDetach();
        }
    }

    private void initButterKnife() {
        ButterKnife.bind(this);
    }
}
