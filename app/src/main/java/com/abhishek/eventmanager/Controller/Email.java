package com.abhishek.eventmanager.Controller;

/**
 * Created by Abhishek on 2/23/2016.
 */
public class Email {

    private String email;
    private String to;
    private String body;

    Email(String email, String to, String body){
        this.email = email;
        this.to = to;
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
