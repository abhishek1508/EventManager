package com.abhishek.eventmanager.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishek.eventmanager.Model.Email;
import com.abhishek.eventmanager.R;

import java.util.List;

/**
 * Created by Abhishek on 2/24/2016.
 */
public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EmailEventsHolder> {

    Context mContext;
    LayoutInflater inflater;
    List<Email> mEmailList;

    public EventsRecyclerViewAdapter(Context context,List<Email>list){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mEmailList = list;
    }

    @Override
    public EmailEventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.layout_view_email_events,parent,false);
        EmailEventsHolder mHolder = new EmailEventsHolder(v);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(EmailEventsHolder holder, int position) {
        Email email = mEmailList.get(position);
        holder.mEmailTo.setText(email.getTo());
        holder.mEmailSubj.setText(email.getSubject());

    }

    @Override
    public int getItemCount() {
        return mEmailList.size();
    }

    public void addNewEmailEvent(Email email){
        mEmailList.add(email);
        notifyItemInserted(mEmailList.size());
    }
}
