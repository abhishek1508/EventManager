package com.abhishek.eventmanager.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhishek.eventmanager.R;

/**
 * Created by Abhishek on 2/24/2016.
 */
public class EmailEventsHolder extends RecyclerView.ViewHolder {

    TextView mEmailTo;
    TextView mEmailSubj;
    TextView mEmailTime;
    TextView mDay;
    TextView mMonth;

    public EmailEventsHolder(View itemView) {
        super(itemView);
        mEmailSubj = (TextView) itemView.findViewById(R.id.text_email_subj);
        mEmailTo = (TextView) itemView.findViewById(R.id.text_email_to);
        mEmailTime = (TextView) itemView.findViewById(R.id.text_email_time);
        mDay = (TextView) itemView.findViewById(R.id.text_view_day);
        mMonth = (TextView) itemView.findViewById(R.id.text_view_month);
    }
}
