package com.desafiozup;

import android.content.Context;
import android.support.annotation.NonNull;

import com.desafiozup.core.util.SharedPreferencesManager;
import com.desafiozup.data.authentication.api.LoginAPI;
import com.desafiozup.data.authentication.model.entity.LoginResponse;
import com.desafiozup.data.authentication.model.entity.User;
import com.desafiozup.data.authentication.model.entity.UserAccount;
import com.desafiozup.presentation.authentication.AuthenticationPresentationContract;
import com.desafiozup.presentation.authentication.presenter.AuthenticationPresenter;
import com.desafiozup.presentation.authentication.ui.AuthenticationActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.Robolectric;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationPresenterTest {

    @Mock
    private SharedPreferencesManager preferencesManager;

    @Mock
    private LoginAPI api;

    @Mock
    private AuthenticationPresentationContract.View view;

    private AuthenticationPresenter presenter;
    private User user;
    private LoginResponse loginResponse;

    @BeforeClass
    public static void setUpRxJava() {
        Scheduler mock = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        initPlugis(mock);
    }


    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() {
        Context context = mock(Context.class);
        presenter = new AuthenticationPresenter(context, view);
    }

    @Test
    public void shouldProgressBarWhenCallService() {
        user = new User();
        user.setCpfEmail("Zup invalido");
        user.setPassword("1111");

        presenter.fetchUser(user);


        verify(view, never()).showLoadingLayout();
    }

    @Test
    public void neverGoToHomeWhenError() {
        user = new User();
        user.setCpfEmail("Zup invalido");
        user.setPassword("1111");
        loginResponse = new LoginResponse();

//        Mockito.doReturn(Observable.just(loginResponse)).when(api).doLogin(user);

        presenter.fetchUser(user);

        verify(view, never()).showSuccessLayout(loginResponse);
    }

    @Test
    public void shouldSaveUserPreferenceWhenSuccess() {
        user = new User();
        user.setCpfEmail("zupuser@gmail.com");
        user.setPassword("Aa#1");
        loginResponse = new LoginResponse();

        UserAccount account = new UserAccount();
        account.setId(1);
        account.setAgency("123");
        account.setBalance(600.0);
        account.setBankAccount("34567");
        account.setName("Zup Mocked");

        loginResponse.setUserAccount(account);

        Mockito.doReturn(Observable.just(loginResponse)).when(api).doLogin(user);

        presenter.fetchUser(user);

        verify(preferencesManager).saveUser(user.getCpfEmail());
    }



    private static void initPlugis(Scheduler mock) {
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> mock);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> mock);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> mock);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> mock);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> mock);
    }
}
