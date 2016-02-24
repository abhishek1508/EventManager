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

public class EmailFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button mSaveButton;
    private EditText mToEditText;
    private EditText mSubjectEditText;
    private EditText mBodyEditText;

    private OnFragmentInteractionListener mListener;

    public EmailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EmailFragment newInstance() {
        EmailFragment fragment = new EmailFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        mSaveButton = (Button) view.findViewById(R.id.save_button);
        mToEditText = (EditText) view.findViewById(R.id.to_editText);
        mSubjectEditText = (EditText) view.findViewById(R.id.body_editText);
        mBodyEditText = (EditText) view.findViewById(R.id.subject_editText);
        mSaveButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        String to = (mToEditText.getText()).toString();
        String sub = (mSubjectEditText.getText()).toString();
        String body = (mBodyEditText.getText()).toString();
        if(!to.equals("") && !sub.equals("") && !body.equals("")){
            if (mListener != null) {
                mListener.closeActivity();
            }
        }
        else
            Toast.makeText(getActivity(),"Enter the values in all the fields",Toast.LENGTH_LONG).show();
    }
}
