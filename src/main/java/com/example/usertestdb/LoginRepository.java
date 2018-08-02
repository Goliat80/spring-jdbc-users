package com.example.usertestdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginRepository {

    @Autowired
    public DataSource dataSource;

    public void addUser(String name, String email, String username, String password) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES(?,?,?,?)", new String[] {"id"} );
            ps.setString(1,name);
            ps.setString(2, email);
            ps.setString(3,username);
            ps.setString(4, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List <Users> getUsers() {
        List <Users> allUsers = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                Users user = new Users(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"), resultSet.getString("username"),
                        resultSet.getString("password"));

                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }
}
