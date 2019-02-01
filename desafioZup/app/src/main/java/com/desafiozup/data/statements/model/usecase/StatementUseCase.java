package com.desafiozup.data.statements.model.usecase;

import com.desafiozup.data.statements.api.StatementsRequester;
import com.desafiozup.data.statements.model.StatementResponse;

import io.reactivex.Observable;

public class StatementUseCase implements StatementModelContract {

    @Override
    public Observable<StatementResponse> getStatementInformation() {
        return StatementsRequester.getInstance().getStatements();
    }
}
