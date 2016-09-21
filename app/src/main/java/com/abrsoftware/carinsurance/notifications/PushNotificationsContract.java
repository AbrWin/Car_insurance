package com.abrsoftware.carinsurance.notifications;

import com.abrsoftware.carinsurance.base.BasePresenter;
import com.abrsoftware.carinsurance.base.BaseView;
import com.abrsoftware.carinsurance.model.PushNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abrwin on 19/09/2016.
 */

public class PushNotificationsContract {
    interface View extends BaseView<Presenter>{
        void showNotifications(ArrayList<PushNotification> notifications);

        void showEmptyState(boolean empty);

        void popPushNotification(PushNotification pushMessage);

    }

    interface Presenter extends BasePresenter{
        void registerAppClient();
        void loadNotifications();
        void savePushMessage(String title, String description,
                             String expiryDate, String discount);
    }
}
