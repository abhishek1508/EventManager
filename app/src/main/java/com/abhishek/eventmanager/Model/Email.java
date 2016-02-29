package com.abhishek.eventmanager.Model;

/**
 * Created by Abhishek on 2/23/2016.
 */
public class Email {

    private String subject;
    private String to;
    private String body;
    private int id;
    private String time;
    private String date;

    public Email(String to, String subject, String body, String time, String date){
        this.subject = subject;
        this.to = to;
        this.body = body;
        this.time = time;
        this.date = date;
    }

    public Email(){
        //Empty constructor
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
}
