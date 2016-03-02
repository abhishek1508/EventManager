package com.abhishek.eventmanager.View;

import com.abhishek.eventmanager.Model.Email;

/**
 * Created by Abhishek on 2/24/2016.
 */
public interface OnManageEventsInteractionListener {

    void commEventDetailsToViewEventsFragment(Email email, int updatedPosition);
}
