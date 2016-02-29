package com.abhishek.eventmanager.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.abhishek.eventmanager.Controller.DBEventHelper;
import com.abhishek.eventmanager.Model.Email;
import com.abhishek.eventmanager.R;

import java.util.List;

public class EmailEventActivity extends AppCompatActivity implements OnViewEventsInteractionListener,OnManageEventsInteractionListener,OnTimeSelectedListener{

    FragmentManager mManager;
    FragmentTransaction mTransaction;
    ViewEmailEventsFragment mViewFragment;
    ManageEmailEventsFragment mManageFragment;
    public static List<Email> mEmailList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showViewEmailFragment();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mManageFragment = ManageEmailEventsFragment.newInstance();
                mManager = getSupportFragmentManager();
                mTransaction = mManager.beginTransaction();
                mTransaction.replace(R.id.container_fragment, mManageFragment, "ManageEmailEvents");
                mTransaction.addToBackStack("ManageEmailEvents");
                fab.setVisibility(View.GONE);
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
        fab.setVisibility(View.VISIBLE);
        Email email = new Email(eventDesc.get(0),eventDesc.get(1),eventDesc.get(2));
        mViewFragment.newCreatedEmailEvents(email);
    }

    @Override
    public void onTimeSelected(String time) {
        mManageFragment.setTime(time);
    }
}
