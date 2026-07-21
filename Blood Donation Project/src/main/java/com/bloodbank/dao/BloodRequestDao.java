package com.bloodbank.dao;

import com.bloodbank.model.BloodRequest;
import com.bloodbank.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BloodRequestDao {
    public long insert(BloodRequest request) {
        String sql = "INSERT INTO blood_request(patient_id, blood_group, quantity, hospital_name, hospital_location, doctor_description, emergency_level, required_date, status) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, request.getPatientId());
            statement.setString(2, request.getBloodGroup());
            statement.setInt(3, request.getQuantity());
            statement.setString(4, request.getHospitalName());
            statement.setString(5, request.getHospitalLocation());
            statement.setString(6, request.getDoctorDescription());
            statement.setString(7, request.getEmergencyLevel());
            statement.setDate(8, Date.valueOf(request.getRequiredDate()));
            statement.setString(9, request.getStatus());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getLong(1) : 0L;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to insert blood request", exception);
        }
    }

    public BloodRequest findById(long id) {
        String sql = "SELECT * FROM blood_request WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return map(resultSet);
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to load blood request", exception);
        }
        return null;
    }

    public List<BloodRequest> listByPatient(long patientId) {
        return list("SELECT * FROM blood_request WHERE patient_id = ? ORDER BY created_at DESC", patientId);
    }

    public List<BloodRequest> listAll() {
        return list("SELECT * FROM blood_request ORDER BY created_at DESC");
    }

    public List<BloodRequest> listByStatus(String status) {
        return list("SELECT * FROM blood_request WHERE status = ? ORDER BY created_at DESC", status);
    }

    public boolean updateStatus(long id, String status) {
        String sql = "UPDATE blood_request SET status = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to update blood request status", exception);
        }
    }

    public long countByStatus(String status) {
        String sql = "SELECT COUNT(*) AS total FROM blood_request WHERE status = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getLong("total") : 0L;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to count blood requests", exception);
        }
    }

    private List<BloodRequest> list(String sql, Object... params) {
        List<BloodRequest> requests = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 0; index < params.length; index++) {
                statement.setObject(index + 1, params[index]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    requests.add(map(resultSet));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to list blood requests", exception);
        }
        return requests;
    }

    private BloodRequest map(ResultSet resultSet) throws Exception {
        BloodRequest request = new BloodRequest();
        request.setId(resultSet.getLong("id"));
        request.setPatientId(resultSet.getLong("patient_id"));
        request.setBloodGroup(resultSet.getString("blood_group"));
        request.setQuantity(resultSet.getInt("quantity"));
        request.setHospitalName(resultSet.getString("hospital_name"));
        request.setHospitalLocation(resultSet.getString("hospital_location"));
        request.setDoctorDescription(resultSet.getString("doctor_description"));
        request.setEmergencyLevel(resultSet.getString("emergency_level"));
        Date date = resultSet.getDate("required_date");
        if (date != null) {
            request.setRequiredDate(date.toLocalDate());
        }
        request.setStatus(resultSet.getString("status"));
        java.sql.Timestamp timestamp = resultSet.getTimestamp("created_at");
        if (timestamp != null) {
            request.setCreatedAt(timestamp.toLocalDateTime());
        }
        return request;
    }
}
