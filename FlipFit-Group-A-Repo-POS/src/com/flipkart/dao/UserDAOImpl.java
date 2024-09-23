package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.utils.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO user (username, password, userid, roleId) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserid());
            statement.setString(4, user.getRoleId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerGymOwner(GymOwner gymOwner) {
        String sql = "INSERT INTO gym_owner (username, userid, name, email, contactNo, age, approval) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gymOwner.getUsername());
            statement.setString(2, gymOwner.getUserid());
            statement.setString(3, gymOwner.getName());
            statement.setString(4, gymOwner.getEmail());
            statement.setString(5, gymOwner.getContactNo());
            statement.setInt(6, gymOwner.getAge());
            statement.setInt(7, 0);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerCustomer(Customer customer) {
        String sql = "INSERT INTO customer (username, userid, name, email, contactNo, age) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getUserid());
            statement.setString(3, customer.getName());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPhone());
            statement.setInt(6, customer.getAge());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = dbutils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"), rs.getString("userid"), rs.getString("roleId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET username = ?, password = ? WHERE userid = ?";
        try (Connection conn = dbutils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserid());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
