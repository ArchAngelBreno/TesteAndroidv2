package com.desafiozup.data.statements.api;

import com.desafiozup.data.statements.model.StatementResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface StatementsAPI {

    @GET("statements/1")
    Observable<StatementResponse> getStatements();
}
