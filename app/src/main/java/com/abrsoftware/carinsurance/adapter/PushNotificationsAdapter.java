package com.abrsoftware.carinsurance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abrsoftware.carinsurance.R;
import com.abrsoftware.carinsurance.model.PushNotification;

import java.util.ArrayList;

/**
 * Created by AbrWin on 20/09/16.
 */

public class PushNotificationsAdapter extends RecyclerView.Adapter<PushNotificationsAdapter.ViewHolder> {
    private ArrayList<PushNotification> pushNotifications = new ArrayList<>();

    public PushNotificationsAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_list_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PushNotification pushNotification = pushNotifications.get(position);
        holder.title.setText(pushNotification.getmTitle());
        holder.description.setText(pushNotification.getmDescription());
        holder.discount.setText(pushNotification.getmDiscount());
        holder.expiryDate.setText(pushNotification.getmExpiryDate());

    }

    @Override
    public int getItemCount() {
        return pushNotifications.size();
    }

    public void replaceData(ArrayList<PushNotification> items){
        setList(items);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<PushNotification> list){
        this.pushNotifications = list;

    }

    public void addItem(PushNotification pushMessage){
        pushNotifications.add(0, pushMessage);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView expiryDate;
        public TextView discount;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            expiryDate = (TextView) itemView.findViewById(R.id.tv_expiry_date);
            discount = (TextView) itemView.findViewById(R.id.tv_discount);
        }
    }
}
