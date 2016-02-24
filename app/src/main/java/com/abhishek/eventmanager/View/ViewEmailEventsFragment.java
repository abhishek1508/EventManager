package com.abhishek.eventmanager.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishek.eventmanager.R;

public class ViewEmailEventsFragment extends Fragment {

    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;*/

    private OnViewEventsInteractionListener mListener;
    private RecyclerView mRecycler;

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
        EventsRecyclerViewAdapter recyclerViewAdapter = new EventsRecyclerViewAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(recyclerViewAdapter);
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

}
