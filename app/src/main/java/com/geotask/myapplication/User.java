package com.geotask.myapplication;

/**
 * Created by Kehan Wang on 2018/2/23.
 */

public class User {
    private String name;
    private String email;
    private String phonenum;

    public User(String name, String email, String phonenum){
        super();
        this.name = name;
        this.email = email;
        this.phonenum = phonenum;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email=email;
    }

    public String getPhonenum(){
        return phonenum;
    }

    public void setPhonenum(){
        this.phonenum = phonenum;
    }
}
