package com.abrsoftware.carinsurance.notifications;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.abrsoftware.carinsurance.R;
import com.abrsoftware.carinsurance.adapter.PushNotificationsAdapter;
import com.abrsoftware.carinsurance.model.PushNotification;

import java.util.List;


public class PushNotificationsFragment extends Fragment implements PushNotificationsContract.View {
    private RecyclerView mRecyclerView;
    private LinearLayout mNoMessagesView;
    private PushNotificationsAdapter mNotificatiosAdapter;

    public PushNotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
        mNotificatiosAdapter = new PushNotificationsAdapter();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_notifications_list);
        mNoMessagesView = (LinearLayout) rootView.findViewById(R.id.noMessages);
        mRecyclerView.setAdapter(mNotificatiosAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showNotifications(List<PushNotification> notifications) {

    }

    @Override
    public void showNoMessagesView() {

    }

    @Override
    public void setPresenter(PushNotificationsContract.Presenter presenter) {

    }
}
