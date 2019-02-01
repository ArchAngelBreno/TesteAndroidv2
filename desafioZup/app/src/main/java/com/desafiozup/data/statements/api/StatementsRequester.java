package com.desafiozup.data.statements.api;

import com.desafiozup.data.statements.model.StatementResponse;

import io.reactivex.Observable;

public class StatementsRequester {

    private static StatementsAPI service = StatementsClient.providesRetrofit().create(StatementsAPI.class);
    private static StatementsRequester statementsRequester;

    public static StatementsRequester getInstance(){
        if (statementsRequester == null){
            statementsRequester = new StatementsRequester();
        }

        return statementsRequester;
    }

    public Observable<StatementResponse> getStatements(){
        return service.getStatements();
    }
}
