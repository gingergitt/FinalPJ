package com.example.appver2;

public class User {
    public String User;
    public String userid;
    public String userage;

    public User () {}

    public User(String userid, String userage) {
        this.userid = userid;
        this.userage = userage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }
}
