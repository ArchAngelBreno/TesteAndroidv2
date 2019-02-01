package com.desafiozup.presentation.home.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.desafiozup.R;
import com.desafiozup.core.base.BaseFragment;
import com.desafiozup.data.statements.model.StatementResponse;
import com.desafiozup.presentation.home.StatementPresentationContract;
import com.desafiozup.presentation.home.presenter.StatementPresenter;
import com.desafiozup.presentation.home.ui.adapter.StatementAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatementFragment extends BaseFragment<StatementPresentationContract.Presenter> implements StatementPresentationContract.View {

    @BindView(R.id.include_error_layout)
    ViewGroup errorLayout;

    @BindView(R.id.tv_most_recently_invoices)
    TextView mostRecentyleInvoicesTitle;


    @BindView(R.id.view_divider)
    View divider;

    @BindView(R.id.pb_home_loading)
    ProgressBar loadingStatementPb;

    @BindView(R.id.rv_home_statements)
    RecyclerView statementRv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        presenter.fetchStatement();
    }

    @Override
    public void showErrorLayout() {
        statementRv.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
        mostRecentyleInvoicesTitle.setVisibility(View.GONE);
        loadingStatementPb.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void populateStatements(StatementResponse statement) {
        StatementAdapter adapter = new StatementAdapter(getContext(),statement.getStatements());
        statementRv.setItemAnimator(new DefaultItemAnimator());
        statementRv.setLayoutManager(new LinearLayoutManager(getContext()));
        statementRv.setAdapter(adapter);
    }

    @Override
    public void showSuccesLayout() {
        statementRv.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
        mostRecentyleInvoicesTitle.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        loadingStatementPb.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingLayout() {
        statementRv.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
        mostRecentyleInvoicesTitle.setVisibility(View.GONE);
        loadingStatementPb.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
    }


    @OnClick(R.id.cl_error_container)
    void onClickError(){
        presenter.fetchStatement();
    }

    @Override
    public StatementPresentationContract.Presenter createPresenter() {
        return new StatementPresenter(this);
    }
}
