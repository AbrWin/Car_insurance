package com.abrsoftware.carinsurance.notifications;

import com.abrsoftware.carinsurance.model.PushNotification;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

/**
 * Created by abrwin on 20/09/2016.
 */

public class PushNotificationsPresenter implements PushNotificationsContract.Presenter {
    private final PushNotificationsContract.View mNotificationView;
    private final FirebaseMessaging mFCMInteractor;

    public PushNotificationsPresenter(PushNotificationsContract.View notificationView, FirebaseMessaging fCMInteractor) {
        mNotificationView = notificationView;
        mFCMInteractor = fCMInteractor;
    }

    @Override
    public void start() {
        registerAppClient();
        loadNotifications();
    }

    @Override
    public void registerAppClient() {
        mFCMInteractor.subscribeToTopic("promos");
    }

    @Override
    public void savePushMessage(String title, String description, String expiryDate, String discount) {

    }

    @Override
    public void loadNotifications() {
        PushNotificationsRepository.getInstance().getPushNotifications(new PushNotificationsRepository.LoadCallback() {
            @Override
            public void onLoaded(ArrayList<PushNotification> notifications) {
                if (notifications.size() > 0) {
                    mNotificationView.showEmptyState(false);
                    mNotificationView.showNotifications(notifications);
                } else {
                    mNotificationView.showEmptyState(true);
                }
            }
        });
    }

}
