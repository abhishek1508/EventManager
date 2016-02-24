package com.abhishek.eventmanager.View;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.abhishek.eventmanager.Controller.DBEventHelper;
import com.abhishek.eventmanager.Model.Email;
import com.abhishek.eventmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ManageEvents extends AppCompatActivity implements OnFragmentInteractionListener, View.OnClickListener{

    private Button mButtonEmail;

    FragmentManager mManager;
    FragmentTransaction mTransaction;

    private DBEventHelper mHelper;
    private SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_events);

        mHelper = new DBEventHelper(getApplicationContext());
        database = mHelper.getWritableDatabase();

        mButtonEmail = (Button) findViewById(R.id.button_email);
        mButtonEmail.setOnClickListener(this);

        Toast.makeText(this,"Select event type by clicking a button",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(List<String> event) {
        Email email = new Email(event.get(0),event.get(1),event.get(2));
        mHelper.addEmail(email);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        Fragment fragment;
        switch(id){
            case R.id.button_email:
                fragment = EmailFragment.newInstance();
                mTransaction.replace(R.id.fragment_container,fragment);
                mTransaction.commit();
                break;
        }

    }
}
