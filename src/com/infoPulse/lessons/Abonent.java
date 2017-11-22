package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoTools.DaoField;
import com.infoPulse.lessons.DaoTools.DaoTable;

@DaoTable(name = "abonent")
public class Abonent {


    // Fields
    @DaoField(name = "abonent_id", id = true)
    private int abonent_id;

    @DaoField(name = "user_id")
    private int user_id;

    @DaoField(name = "phone_number")
    private int phone_number;

    @DaoField(name = "abonent_status")
    private String abonent_status;

    @DaoField(name = "abonent_balance")
    private int abonent_balance;


    // Constructors
    public Abonent() {}


    // Getter and Setter
    public int getAbonent_id() {
        return abonent_id;
    }

    public void setAbonent_id(int abonent_id) {
        this.abonent_id = abonent_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getAbonent_status() {
        return abonent_status;
    }

    public void setAbonent_status(String abonent_status) {
        this.abonent_status = abonent_status;
    }

    public int getAbonent_balance() {
        return abonent_balance;
    }

    public void setAbonent_balance(int abonent_balance) {
        this.abonent_balance = abonent_balance;
    }


    @Override
    public String toString() {
        return "Abonent{" +
                "abonent_id=" + abonent_id +
                ", user_id=" + user_id +
                ", phone_number=" + phone_number +
                ", abonent_status='" + abonent_status + '\'' +
                ", abonent_balance=" + abonent_balance +
                '}';
    }
}
