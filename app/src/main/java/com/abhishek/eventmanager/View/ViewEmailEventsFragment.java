package com.abhishek.eventmanager.View;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishek.eventmanager.Controller.DBEventHelper;
import com.abhishek.eventmanager.Model.Email;
import com.abhishek.eventmanager.R;

import java.util.List;

public class ViewEmailEventsFragment extends Fragment {

    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;*/

    private OnViewEventsInteractionListener mListener;
    private RecyclerView mRecycler;

    private DBEventHelper mHelper;
    private SQLiteDatabase mDatabase;

    private EventsRecyclerViewAdapter mRecyclerViewAdapter = null;

    public ViewEmailEventsFragment() {
        // Required empty public constructor
    }

    public static ViewEmailEventsFragment newInstance() {
        ViewEmailEventsFragment fragment = new ViewEmailEventsFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new DBEventHelper(getActivity());
        mDatabase = mHelper.getWritableDatabase();
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_email_events, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.viewEmailRecyclerView);
        setRecyclerView();
        return view;
    }

    private void setRecyclerView(){

        mRecyclerViewAdapter = new EventsRecyclerViewAdapter(getActivity(),mHelper.getAllEmailEvents());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mRecyclerViewAdapter);
    }

    public void onButtonPressed() {
        if (mListener != null) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnViewEventsInteractionListener) {
            mListener = (OnViewEventsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnViewEventsInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void newCreatedEmailEvents(Email e){
        mHelper.addEmail(e);
        mRecyclerViewAdapter.addNewEmailEvent(e);
    }

}
