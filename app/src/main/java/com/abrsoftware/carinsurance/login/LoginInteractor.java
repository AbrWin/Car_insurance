package com.abrsoftware.carinsurance.login;

/**
 * Created by AbrWin on 11/08/16.
 */
public class LoginInteractor {
    interface Callback {

        void onEmailError(String msg);

        void onPasswordError(String msg);

        void onNetworkConnectFailed();

        void onBeUserResolvableError(int errorCode);

        void onGooglePlayServicesFailed();

        void onAuthFailed(String msg);

        void onAuthSuccess();
    }
}
