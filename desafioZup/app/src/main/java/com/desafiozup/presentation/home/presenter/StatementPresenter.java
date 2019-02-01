package com.desafiozup.presentation.home.presenter;

import com.desafiozup.core.base.BasePresenter;
import com.desafiozup.data.statements.model.usecase.StatementModelContract;
import com.desafiozup.data.statements.model.usecase.StatementUseCase;
import com.desafiozup.presentation.home.StatementPresentationContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StatementPresenter extends BasePresenter implements StatementPresentationContract.Presenter {

    private StatementModelContract useCase;
    private StatementPresentationContract.View view;

    public StatementPresenter(StatementPresentationContract.View view) {
        this.view = view;
        this.useCase = new StatementUseCase();
    }

    @Override
    public void fetchStatement() {
        view.showLoadingLayout();
        Disposable disposable = useCase.getStatementInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                            view.showSuccesLayout();
                            view.populateStatements(it);
                        },
                        error -> view.showErrorLayout());

        compositeDisposable.add(disposable);
    }
}
