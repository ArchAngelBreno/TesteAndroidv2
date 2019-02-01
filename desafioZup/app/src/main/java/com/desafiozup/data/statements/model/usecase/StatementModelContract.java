package com.desafiozup.data.statements.model.usecase;

import com.desafiozup.data.statements.model.StatementResponse;

import io.reactivex.Observable;

public interface StatementModelContract {

    Observable<StatementResponse> getStatementInformation();
}
