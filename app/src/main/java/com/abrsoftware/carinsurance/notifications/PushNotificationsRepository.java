package com.abrsoftware.carinsurance.notifications;

import android.support.v4.app.LoaderManager;
import android.support.v4.util.ArrayMap;

import com.abrsoftware.carinsurance.model.PushNotification;

import java.util.ArrayList;

/**
 * Created by abrwin on 19/09/2016.
 */

public class PushNotificationsRepository {
    private static ArrayMap<String, PushNotification>LOCAL_PUSH_NOTIFICATIONS = new ArrayMap<>();
    private static PushNotificationsRepository INSTANCE;

    public PushNotificationsRepository() {
    }

    public static PushNotificationsRepository getInstance(){
        if(INSTANCE == null){
            return new PushNotificationsRepository();
        }else{
            return INSTANCE;
        }
    }

    public void getPushNotifications(LoadCallback callbacks){
        callbacks.onLoaded(new ArrayList<>(LOCAL_PUSH_NOTIFICATIONS.values()));
    }

    public void savePushNotification(PushNotification notification){
        LOCAL_PUSH_NOTIFICATIONS.put(notification.getId(), notification);
    }


    public interface LoadCallback{
        void onLoaded(ArrayList<PushNotification> notifications);
    }
}
