package com.abhishek.eventmanager.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishek.eventmanager.R;

/**
 * Created by Abhishek on 2/24/2016.
 */
public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EmailEventsHolder> {

    Context mContext;
    LayoutInflater inflater;

    public EventsRecyclerViewAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public EmailEventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.layout_view_email_events,parent,false);
        EmailEventsHolder mHolder = new EmailEventsHolder(v);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(EmailEventsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
