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
    List<Email> mEmailList;
    private SparseBooleanArray selectedItems;

    public EventsRecyclerViewAdapter(Context context,List<Email>list){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mEmailList = list;
        this.selectedItems = new SparseBooleanArray();
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
        holder.itemView.setActivated(selectedItems.get(position,false));
    }

    @Override
    public int getItemCount() {
        return mEmailList.size();
    }

    public void addNewEmailEvent(Email email){
        mEmailList.add(email);
        notifyItemInserted(mEmailList.size());
    }

    public void deleteRecyclerViewItem(){

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
