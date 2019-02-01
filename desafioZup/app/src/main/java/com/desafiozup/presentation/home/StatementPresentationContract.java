package com.desafiozup.presentation.home;

import com.desafiozup.core.base.BaseContract;
import com.desafiozup.data.statements.model.StatementResponse;

public interface StatementPresentationContract {

    interface View extends BaseContract.View<StatementPresentationContract.Presenter>{
        void showErrorLayout();
        void populateStatements(StatementResponse statement);
        void showSuccesLayout();
        void showLoadingLayout();
    }

    interface Presenter extends BaseContract.Presenter{
        void fetchStatement();
    }
}
