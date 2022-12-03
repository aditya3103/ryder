package com.example.ryderr.models;

public class Student {
    String uid;
    String displayName;
    String emailId;

    public Student() {

    }

    public String getUid() {
        return uid;
    }

    public Student(String uid, String displayName, String emailId) {
        this.uid = uid;
        this.displayName = displayName;
        this.emailId = emailId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
