package com.abhishek.eventmanager.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishek.eventmanager.Model.Email;
import com.abhishek.eventmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 2/24/2016.
 */
public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EmailEventsHolder> {

    Context mContext;
    LayoutInflater inflater;
    private SparseBooleanArray selectedItems;

    public EventsRecyclerViewAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.selectedItems = new SparseBooleanArray();
    }

    @Override
    public EmailEventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.layout_view_email_events, parent, false);
        return new EmailEventsHolder(v);
    }

    @Override
    public void onBindViewHolder(EmailEventsHolder holder, int position) {
        Email email =  EmailEventActivity.mEmailList.get(position);
        holder.mEmailTo.setText(email.getTo());
        holder.mEmailSubj.setText(email.getSubject());
        holder.mEmailTime.setText(email.getTime());
        String[] date = splitDayMonth(email.getDate());
        holder.mDay.setText(date[0]);
        holder.mMonth.setText(date[1]);
        holder.itemView.setActivated(selectedItems.get(position,false));
    }

    @Override
    public int getItemCount() {
        return  EmailEventActivity.mEmailList.size();
    }

    public String[] splitDayMonth(String date){
        return date.split(" ");
    }

    public void addNewEmailEvent(Email email){
        EmailEventActivity.mEmailList.add(email);
        notifyItemInserted( EmailEventActivity.mEmailList.size());
    }

    public void updateExistingEmailReminder(Email email, int pos){
        /*EmailEventActivity.mEmailList.remove(pos);
        EmailEventActivity.mEmailList.add(pos,email);*/
        notifyItemChanged(pos,email);
    }

    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
}
