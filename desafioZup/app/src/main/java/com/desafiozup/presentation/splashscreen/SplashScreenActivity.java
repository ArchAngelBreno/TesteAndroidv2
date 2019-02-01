package com.desafiozup.presentation.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.desafiozup.R;
import com.desafiozup.core.util.AnimationUtil;
import com.desafiozup.presentation.authentication.ui.AuthenticationActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Completable.complete()
                .delay(2, TimeUnit.SECONDS)
                .doOnComplete(this::goToAuthenticationActivity)
                .subscribe();

        AnimationUtil.fadeInSlow(findViewById(R.id.img_logo));
    }


    private void goToAuthenticationActivity() {
        overridePendingTransition(R.anim.activity_transition_fade_in,R.anim.activity_transition_fade_out);
        startActivity(new Intent(this, AuthenticationActivity.class));
        finishAffinity();
    }
}
