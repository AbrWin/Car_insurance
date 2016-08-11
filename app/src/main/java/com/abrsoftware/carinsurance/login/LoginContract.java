package com.abrsoftware.carinsurance.login;

import com.abrsoftware.carinsurance.base.BasePresenter;
import com.abrsoftware.carinsurance.base.BaseView;

/**
 * Created by AbrWin on 11/08/16.
 */
public class LoginContract {
    interface View extends BaseView<Presenter> {
        void showprogress(Boolean show);
        void setMailError(String error);
        void setPasswordError(String error);
        void showLoginError(String msg);
        void showPushNotificaton();
        void showGooglePlayServicesDialog(int errorCode);
        void showGooglePlayServicesError();
        void showNetworkError();
    }

    interface  Presenter extends BasePresenter{
        void attemptLogin(String mail, String password);
    }
}
