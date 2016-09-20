package com.abrsoftware.carinsurance.notifications;

import com.abrsoftware.carinsurance.base.BasePresenter;
import com.abrsoftware.carinsurance.base.BaseView;
import com.abrsoftware.carinsurance.model.PushNotification;

import java.util.List;

/**
 * Created by abrwin on 19/09/2016.
 */

public class PushNotificationsContract {
    interface View extends BaseView<Presenter>{
        void showNotifications(List<PushNotification> notifications);
        void showNoMessagesView();

    }

    interface Presenter extends BasePresenter{
        void registerAppClient();
        void loadNotifications();
    }
}
