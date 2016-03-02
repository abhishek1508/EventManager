package com.abhishek.eventmanager.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abhishek.eventmanager.Model.Email;
import com.abhishek.eventmanager.R;

public class ManageEmailEventsFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mToBeUpdatedPosition = -1;
    private boolean sToBeUpdated = false;

    private String mTimeText = "Set Time";
    private String mDateText = "Set Date";
    private String mTime = "";
    private String mDate = "";

    private OnManageEventsInteractionListener mListener;
    private Email mEmail = null;

    private EditText mTo;
    private EditText mSubj;
    private EditText mBody;
    private Button mSaveButton;
    private TimeAndDateCustomView mTimeView;
    private TimeAndDateCustomView mDateView;

    public ManageEmailEventsFragment() {
        // Required empty public constructor
    }

    public static ManageEmailEventsFragment newInstance(int pos, Email email) {
        ManageEmailEventsFragment fragment = new ManageEmailEventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, pos);
        args.putParcelable(ARG_PARAM2, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mToBeUpdatedPosition = getArguments().getInt(ARG_PARAM1);
            if (mToBeUpdatedPosition >= 0) {
                mEmail = getArguments().getParcelable(ARG_PARAM2);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_email_events, container, false);
        mTo = (EditText) view.findViewById(R.id.to_editText);
        mSubj = (EditText) view.findViewById(R.id.subject_editText);
        mBody = (EditText) view.findViewById(R.id.body_editText);
        mSaveButton = (Button) view.findViewById(R.id.save_button);
        mTimeView = (TimeAndDateCustomView) view.findViewById(R.id.timeView);
        mDateView = (TimeAndDateCustomView) view.findViewById(R.id.dateView);

        mSaveButton.setOnClickListener(this);
        mTimeView.setOnClickListener(this);
        mDateView.setOnClickListener(this);

        loadViewsWithEmailData();
        return view;
    }

    public void loadViewsWithEmailData() {
        if (mEmail != null) {
            mTo.setText(mEmail.getTo());
            mSubj.setText(mEmail.getSubject());
            mBody.setText(mEmail.getBody());
            setTime(mEmail.getTime());
            setDate(mEmail.getDate());
            sToBeUpdated = true;
        } else {
            setTime(mTimeText);
            setDate(mDateText);
        }
    }

    public void onSaveButtonClicked(Email email, int pos) {
        if (mListener != null) {
            mListener.commEventDetailsToViewEventsFragment(email, pos);
            //Close the fragment after the event has been saved
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnManageEventsInteractionListener) {
            mListener = (OnManageEventsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnManageEventsInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                String to = String.valueOf(mTo.getText());
                String subj = String.valueOf(mSubj.getText());
                String body = String.valueOf(mBody.getText());
                if (!to.equals("") && !subj.equals("") && !body.equals("") && !mTime.equals("") && !mDate.equals("")) {
                    mEmail.setTo(to);
                    mEmail.setSubject(subj);
                    mEmail.setBody(body);
                    mEmail.setTime(mTime);
                    mEmail.setDate(mDate);
                    onSaveButtonClicked(mEmail, mToBeUpdatedPosition);
                }
                else
                    Toast.makeText(getActivity(), "Enter values in all the fields", Toast.LENGTH_LONG).show();
                break;
            case R.id.timeView:
                SetTime timeFragment = new SetTime();
                timeFragment.show(getFragmentManager(), "TimePickerDialog");
                break;
            case R.id.dateView:
                SetDate dateFragment = new SetDate();
                dateFragment.show(getFragmentManager(), "DatePickerDialog");
                break;
        }
    }

    public void setTime(String time) {
        mTime = time;
        mTimeView.setTimeText(time);
    }

    public void setDate(String date) {
        mDate = date;
        mDateView.setDateText(date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTime = "";
        mDate = "";
    }


}
