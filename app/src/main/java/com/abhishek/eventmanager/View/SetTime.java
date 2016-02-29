package com.abhishek.eventmanager.View;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.abhishek.eventmanager.R;

import java.util.Calendar;

/**
 * Created by Abhishek on 2/28/2016.
 */
public class SetTime extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private OnTimeSelectedListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(),this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(getActivity());
        mListener = (OnTimeSelectedListener) getActivity();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String am_pm = "AM";
        if(hourOfDay > 12) {
            hourOfDay -= 12;
            am_pm = "PM";
        }
        mListener.onTimeSelected(String.valueOf(hourOfDay)+":"+String.valueOf(minute)+am_pm);
    }
}
