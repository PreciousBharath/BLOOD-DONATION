package com.bloodbank.dao;

import com.bloodbank.model.Patient;
import com.bloodbank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
    public long insert(Patient patient) {
        String sql = "INSERT INTO patient(name, phone, email, address, password, status) VALUES(?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getPhone());
            statement.setString(3, patient.getEmail());
            statement.setString(4, patient.getAddress());
            statement.setString(5, patient.getPassword());
            statement.setString(6, patient.getStatus());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getLong(1) : 0L;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to insert patient", exception);
        }
    }

    public Patient findById(long id) {
        return find("SELECT * FROM patient WHERE id = ?", id);
    }

    public Patient findByEmail(String email) {
        return find("SELECT * FROM patient WHERE email = ?", email);
    }

    public Patient authenticate(String email, String password) {
        Patient patient = findByEmail(email);
        if (patient != null && com.bloodbank.util.PasswordUtil.matches(password, patient.getPassword())) {
            return patient;
        }
        return null;
    }

    public boolean updateStatus(long id, String status) {
        String sql = "UPDATE patient SET status = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to update patient status", exception);
        }
    }

    public List<Patient> listAll() {
        return list("SELECT * FROM patient ORDER BY created_at DESC");
    }

    public List<Patient> listByStatus(String status) {
        return list("SELECT * FROM patient WHERE status = ? ORDER BY created_at DESC", status);
    }

    public long countByStatus(String status) {
        String sql = "SELECT COUNT(*) AS total FROM patient WHERE status = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getLong("total") : 0L;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to count patients", exception);
        }
    }

    private Patient find(String sql, Object param) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, param);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return map(resultSet);
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to load patient", exception);
        }
        return null;
    }

    private List<Patient> list(String sql, Object... params) {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 0; index < params.length; index++) {
                statement.setObject(index + 1, params[index]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    patients.add(map(resultSet));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to list patients", exception);
        }
        return patients;
    }

    private Patient map(ResultSet resultSet) throws Exception {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong("id"));
        patient.setName(resultSet.getString("name"));
        patient.setPhone(resultSet.getString("phone"));
        patient.setEmail(resultSet.getString("email"));
        patient.setAddress(resultSet.getString("address"));
        patient.setPassword(resultSet.getString("password"));
        patient.setStatus(resultSet.getString("status"));
        java.sql.Timestamp timestamp = resultSet.getTimestamp("created_at");
        if (timestamp != null) {
            patient.setCreatedAt(timestamp.toLocalDateTime());
        }
        return patient;
    }
}
