package com.infoPulse.lessons.DaoObjects;

import com.infoPulse.lessons.DaoTools.ConnectionSql;
import com.infoPulse.lessons.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public class DaoUser {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public DaoUser() {
        this.connection = ConnectionSql.getInstance().getConnection();
    }

    public User getForID(int id) throws SQLException {
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT * from user where user_id=?");
            preparedStatement.setInt(1,1);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultSet.next();
        user.setUser_id(resultSet.getInt("user_id"));
        user.setUser_login(resultSet.getString("user_login"));
        user.setUser_password(resultSet.getString("user_password"));

        return user;
    }

    public void addFirstUser() {
        try {
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO user (user_login, user_password) VALUES (?, ?)");
            preparedStatement.setString(1, "user1");
            preparedStatement.setString(2, "pass1");
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO user (user_login, user_password) VALUES (?, ?)");
            preparedStatement.setString(1, user.getUser_login());
            preparedStatement.setString(2, user.getUser_password());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
