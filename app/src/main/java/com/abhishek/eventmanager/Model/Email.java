package com.abhishek.eventmanager.Model;

/**
 * Created by Abhishek on 2/23/2016.
 */
public class Email {

    private String subject;
    private String to;
    private String body;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Email(String to, String subject, String body){
        this.subject = subject;
        this.to = to;
        this.body = body;
    }

    public Email(){
        //Empty constructor
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
