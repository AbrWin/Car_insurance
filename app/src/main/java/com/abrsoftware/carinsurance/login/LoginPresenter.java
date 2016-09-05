package com.abrsoftware.carinsurance.login;

import android.support.annotation.NonNull;

/**
 * Created by json on 17/08/16.
 */
public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.Callback{

    private final LoginContract.View mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenter(@NonNull LoginContract.View loginView,
                          @NonNull LoginInteractor loginInteractor){
        mLoginView = loginView;
        loginView.setPresenter(this);
        mLoginInteractor = loginInteractor;
    }

    @Override
    public void attemptLogin(String mail, String password) {

    }

    @Override
    public void start() {

    }

    @Override
    public void onEmailError(String msg) {

    }

    @Override
    public void onPasswordError(String msg) {

    }

    @Override
    public void onNetworkConnectFailed() {

    }

    @Override
    public void onBeUserResolvableError(int errorCode) {

    }

    @Override
    public void onGooglePlayServicesFailed() {

    }

    @Override
    public void onAuthFailed(String msg) {

    }

    @Override
    public void onAuthSuccess() {

    }
}
