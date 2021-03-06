package com.abhishek.eventmanager.View;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abhishek.eventmanager.Controller.DBEventHelper;
import com.abhishek.eventmanager.Model.Email;
import com.abhishek.eventmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ViewEmailEventsFragment extends Fragment implements ActionMode.Callback,RecyclerView.OnItemTouchListener,View.OnClickListener{

    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;*/

    private OnViewEventsInteractionListener mListener;
    private RecyclerView mRecycler;

    private DBEventHelper mHelper;
    private SQLiteDatabase mDatabase;

    private EventsRecyclerViewAdapter mRecyclerViewAdapter = null;

    GestureDetectorCompat gestureDetector;
    ActionMode actionMode;

    public ViewEmailEventsFragment() {
        // Required empty public constructor
    }

    public static ViewEmailEventsFragment newInstance() {
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return new ViewEmailEventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        EmailEventActivity.mEmailList = mHelper.getAllEmailEvents();
        gestureDetector = new GestureDetectorCompat(getActivity(),new RecyclerViewDemoOnGestureListener());
        mRecyclerViewAdapter = new EventsRecyclerViewAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mRecyclerViewAdapter);
        mRecycler.addOnItemTouchListener(this);
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

    public void updateExistingEmailReminder(Email e, int pos){
        int i = mHelper.updateEmailReminder(e);
        mRecyclerViewAdapter.updateExistingEmailReminder(e,pos);
    }

    public void deleteFromEmailTable(int pos){
        Email e =  EmailEventActivity.mEmailList.get(pos);
        EmailEventActivity.mEmailList.remove(pos);
        mHelper.deleteEmailEventsFromTable(e);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_cab_recyclerviewdemoactivity, menu);
        //fab.setVisibility(View.GONE);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                List<Integer> selectedItemPositions = mRecyclerViewAdapter.getSelectedItems();
                int currPos;
                for (int i = selectedItemPositions.size()-1; i >= 0; i--) {
                    currPos = selectedItemPositions.get(i);
                    deleteFromEmailTable(currPos);
                    //adapter.removeData(currPos);
                }
                actionMode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        this.actionMode = null;
        mRecyclerViewAdapter.clearSelections();
        //fab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onClick(View v) {
        if(v != null) {
            if (v.getId() == R.id.container_list_item) {
                // item click
                int position = mRecycler.getChildPosition(v);
                if (actionMode != null) {
                    myToggleSelection(position);
                    return;
                }
                openEmailReminder(position);
            }
        }
    }

    private void openEmailReminder(int pos){
        Email email = EmailEventActivity.mEmailList.get(pos);
        ((EmailEventActivity) getActivity()).showManageEmailFragment(pos, email);
    }

    private void myToggleSelection(int selected_items) {
        mRecyclerViewAdapter.toggleSelection(selected_items);
        String title = getString(R.string.selected_count, mRecyclerViewAdapter.getSelectedItemCount());
        actionMode.setTitle(title);
    }

    private class RecyclerViewDemoOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = mRecycler.findChildViewUnder(e.getX(), e.getY());
            onClick(view);
            return super.onSingleTapConfirmed(e);
        }

        public void onLongPress(MotionEvent e) {
            View view = mRecycler.findChildViewUnder(e.getX(), e.getY());
            if (actionMode != null) {
                return;
            }
            // Start the CAB using the ActionMode.Callback defined above
            actionMode = getActivity().startActionMode(ViewEmailEventsFragment.this);
            int idx = mRecycler.getChildPosition(view);
            myToggleSelection(idx);
            super.onLongPress(e);
        }
    }
}
