package com.abhishek.eventmanager.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abhishek on 2/23/2016.
 */
public class Email implements Parcelable {

    private String subject;
    private String to;
    private String body;
    private int id;
    private String time;
    private String date;

    public Email(String to, String subject, String body, String time, String date) {
        this.subject = subject;
        this.to = to;
        this.body = body;
        this.time = time;
        this.date = date;
    }

    public Email() {
        //Empty constructor
    }

    public Email(Parcel input) {
        this.id = input.readInt();
        this.to = input.readString();
        this.subject = input.readString();
        this.body = input.readString();
        this.time = input.readString();
        this.date = input.readString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(to);
        dest.writeString(subject);
        dest.writeString(body);
        dest.writeString(time);
        dest.writeString(date);
    }

    public static final Parcelable.Creator<Email> CREATOR = new Parcelable.Creator<Email>() {

        @Override
        public Email createFromParcel(Parcel source) {
            return new Email(source);  //using parcelable constructor
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
