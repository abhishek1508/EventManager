package com.abhishek.eventmanager.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abhishek.eventmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ManageEmailEventsFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String mTimeText = "Set Time";
    private String mDateText = "Set Date";

    private OnManageEventsInteractionListener mListener;

    private EditText mTo;
    private EditText mSubj;
    private EditText mBody;
    private Button mSaveButton;
    private TimeAndDateCustomView mTimeView;
    private TimeAndDateCustomView mDateView;

    public ManageEmailEventsFragment() {
        // Required empty public constructor
    }

    public static ManageEmailEventsFragment newInstance() {
        ManageEmailEventsFragment fragment = new ManageEmailEventsFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
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

        setTime(mTimeText);
        setDate(mDateText);
        return view;
    }

    public void onSaveButtonClicked(List<String> emailEventDesc) {
        if (mListener != null) {
            mListener.commEventDetailsToViewEventsFragment(emailEventDesc);
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
        switch (v.getId()){
            case R.id.save_button:
                String to = String.valueOf(mTo.getText());
                String subj = String.valueOf(mSubj.getText());
                String body = String.valueOf(mBody.getText());
                if(!to.equals("") && !subj.equals("") && !body.equals("")){
                    List<String> mEmailEventDesc = new ArrayList<>();
                    mEmailEventDesc.add(to);
                    mEmailEventDesc.add(subj);
                    mEmailEventDesc.add(body);
                    onSaveButtonClicked(mEmailEventDesc);
                }
                else
                    Toast.makeText(getActivity(),"Enter values in all the fields",Toast.LENGTH_LONG).show();
                break;
            case R.id.timeView:
                SetTime fragment = new SetTime();
                fragment.show(getFragmentManager(),"TimePickerDialog");
                break;
            case R.id.dateView:
                break;
        }
    }

    public void setTime(String time){
        mTimeView.setTimeText(time);
    }

    public void setDate(String date){
        mDateView.setDateText(date);
    }
}
