package com.bloodbank.dao;

import com.bloodbank.model.Admin;
import com.bloodbank.util.DBConnection;
import com.bloodbank.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDao {
    public long countAdmins() {
        String sql = "SELECT COUNT(*) AS total FROM admin";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next() ? resultSet.getLong("total") : 0L;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to count admins", exception);
        }
    }

    public long insert(String username, String passwordHash) {
        String sql = "INSERT INTO admin(username, password) VALUES(?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                return resultSet.next() ? resultSet.getLong(1) : 0L;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to insert admin", exception);
        }
    }

    public Admin authenticate(String username, String password) {
        String sql = "SELECT id, username, password FROM admin WHERE username = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next() && PasswordUtil.matches(password, resultSet.getString("password"))) {
                    Admin admin = new Admin();
                    admin.setId(resultSet.getLong("id"));
                    admin.setUsername(resultSet.getString("username"));
                    admin.setPassword(resultSet.getString("password"));
                    return admin;
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to authenticate admin", exception);
        }
        return null;
    }
}
