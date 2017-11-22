package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoTools.DaoField;
import com.infoPulse.lessons.DaoTools.DaoTable;

@DaoTable(name = "user")
public class User {

    // Fields
    @DaoField(name = "user_id", id = true)
    private int user_id;

    @DaoField(name = "user_login")
    private String user_login;

    @DaoField(name = "user_password")
    private  String user_password;


    // Constructors
    public User() {}


    // Getter and Setter
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_login='" + user_login + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
