package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoObjects.DaoObject;

import java.util.List;

public class MainL {
    public static void main(String[] args) {

        DaoObject<User> daoObjectUser = new DaoObject<User>(User.class);
        DaoObject<Abonent> abonentDaoObject = new DaoObject<>(Abonent.class);

        List<User> userList;
        List<Abonent> abonentList;

        User user =  new User();
        Abonent abonent = new Abonent();


        // Create and Insert Users to database
        user.setUser_login("user12");
        user.setUser_password("pass12");

        daoObjectUser.add(user);

        user.setUser_login("user15");
        user.setUser_password("pass15");

        daoObjectUser.add(user);


        // Download some Users from database
        userList = daoObjectUser.getAll();
        for (User user1 : userList) {
            System.out.println(user1.toString());
        }


        // Create and Insert Abonents to database
        abonent.setUser_id(userList.get(0).getUser_id());
        abonent.setPhone_number(672310045);
        abonent.setAbonent_status("Active");
        abonent.setAbonent_balance(150);

        abonentDaoObject.add(abonent);

        abonent.setUser_id(userList.get(1).getUser_id());
        abonent.setPhone_number(675430764);
        abonent.setAbonent_status("Active");
        abonent.setAbonent_balance(200);

        abonentDaoObject.add(abonent);


        System.out.println("------------");


        // Download some Abonents from database
        abonentList = abonentDaoObject.getAll();
        Abonent abonent2 = new Abonent();
        abonent2 = abonentDaoObject.getForID(abonentList.get(1).getAbonent_id());
        System.out.println(abonent2.toString());

    }
}
