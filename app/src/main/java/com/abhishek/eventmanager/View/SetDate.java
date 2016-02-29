package com.abhishek.eventmanager.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.abhishek.eventmanager.Controller.Month;

import java.util.Calendar;

/**
 * Created by Abhishek on 2/28/2016.
 */
public class SetDate extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private onDateSelectedListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (onDateSelectedListener) activity;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mListener.onDateSet(String.valueOf(dayOfMonth)+" " +String.valueOf(Month.values()[monthOfYear]));
    }
}
