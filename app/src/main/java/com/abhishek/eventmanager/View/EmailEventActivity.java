package com.abhishek.eventmanager.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.abhishek.eventmanager.R;

import java.util.List;

public class EmailEventActivity extends AppCompatActivity implements OnViewEventsInteractionListener,OnManageEventsInteractionListener{

    FragmentManager mManager;
    FragmentTransaction mTransaction;
    ViewEmailEventsFragment mViewFragment;
    ManageEmailEventsFragment mManageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showViewEmailFragment();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mManageFragment = ManageEmailEventsFragment.newInstance();
                mManager = getSupportFragmentManager();
                mTransaction = mManager.beginTransaction();
                mTransaction.replace(R.id.container_fragment, mManageFragment, "ManageEmailEvents");
                mTransaction.addToBackStack("MessageEmailEvents");
                mTransaction.commit();
            }
        });
    }

    private void showViewEmailFragment(){
        mViewFragment = ViewEmailEventsFragment.newInstance();
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.replace(R.id.container_fragment, mViewFragment, "ViewEmailEvents");
        mTransaction.commit();
    }

    @Override
    public void commEventDetailsToViewEventsFragment(List<String> eventDesc) {

    }
}
