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
        mLoginView.showprogress(true);
        mLoginInteractor.login(mail, password, this);
    }

    @Override
    public void start() {
        // Comprobar si el usuario está logueado
    }

    @Override
    public void onEmailError(String msg) {
        mLoginView.showprogress(false);
        mLoginView.setMailError(msg);
    }

    @Override
    public void onPasswordError(String msg) {
        mLoginView.showprogress(false);
        mLoginView.setPasswordError(msg);
    }

    @Override
    public void onNetworkConnectFailed() {
        mLoginView.showprogress(false);
        mLoginView.showNetworkError();
    }

    @Override
    public void onBeUserResolvableError(int errorCode) {
        mLoginView.showprogress(false);
        mLoginView.showGooglePlayServicesDialog(errorCode);
    }

    @Override
    public void onGooglePlayServicesFailed() {
        mLoginView.showGooglePlayServicesError();
    }

    @Override
    public void onAuthFailed(String msg) {
        mLoginView.showprogress(false);
        mLoginView.showLoginError(msg);
    }

    @Override
    public void onAuthSuccess() {
        mLoginView.showPushNotificaton();
    }
}
