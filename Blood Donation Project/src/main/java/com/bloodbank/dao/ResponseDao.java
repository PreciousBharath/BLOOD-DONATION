package com.bloodbank.dao;

import com.bloodbank.model.DonorResponse;
import com.bloodbank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResponseDao {
    public boolean saveOrUpdate(long requestId, long donorId, String response) {
        String sql = """
                INSERT INTO donor_response(request_id, donor_id, response, response_date)
                VALUES(?,?,?,CURRENT_TIMESTAMP)
                ON DUPLICATE KEY UPDATE response = VALUES(response), response_date = CURRENT_TIMESTAMP
                """;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, requestId);
            statement.setLong(2, donorId);
            statement.setString(3, response);
            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to save donor response", exception);
        }
    }

    public List<DonorResponse> listByRequest(long requestId) {
        String sql = """
                SELECT r.id, r.request_id, r.donor_id, r.response, r.response_date,
                       d.name AS donor_name, d.phone, d.email, d.city, d.blood_group
                FROM donor_response r
                JOIN donor d ON d.id = r.donor_id
                WHERE r.request_id = ?
                ORDER BY r.response_date DESC
                """;
        List<DonorResponse> responses = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, requestId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    responses.add(map(resultSet));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to list request responses", exception);
        }
        return responses;
    }

    public List<DonorResponse> listAcceptedDonorsByRequest(long requestId) {
        String sql = """
                SELECT r.id, r.request_id, r.donor_id, r.response, r.response_date,
                       d.name AS donor_name, d.phone, d.email, d.city, d.blood_group
                FROM donor_response r
                JOIN donor d ON d.id = r.donor_id
                WHERE r.request_id = ? AND r.response = 'ACCEPTED'
                ORDER BY r.response_date DESC
                """;
        List<DonorResponse> responses = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, requestId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    responses.add(map(resultSet));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to list accepted donors", exception);
        }
        return responses;
    }

    public List<DonorResponse> listByDonor(long donorId) {
        String sql = """
                SELECT r.id, r.request_id, r.donor_id, r.response, r.response_date,
                       d.name AS donor_name, d.phone, d.email, d.city, d.blood_group
                FROM donor_response r
                JOIN donor d ON d.id = r.donor_id
                WHERE r.donor_id = ?
                ORDER BY r.response_date DESC
                """;
        List<DonorResponse> responses = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, donorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    responses.add(map(resultSet));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to list donor history", exception);
        }
        return responses;
    }

    private DonorResponse map(ResultSet resultSet) throws Exception {
        DonorResponse response = new DonorResponse();
        response.setId(resultSet.getLong("id"));
        response.setRequestId(resultSet.getLong("request_id"));
        response.setDonorId(resultSet.getLong("donor_id"));
        response.setResponse(resultSet.getString("response"));
        java.sql.Timestamp timestamp = resultSet.getTimestamp("response_date");
        if (timestamp != null) {
            response.setResponseDate(timestamp.toLocalDateTime());
        }
        response.setDonorName(resultSet.getString("donor_name"));
        response.setPhone(resultSet.getString("phone"));
        response.setEmail(resultSet.getString("email"));
        response.setCity(resultSet.getString("city"));
        response.setBloodGroup(resultSet.getString("blood_group"));
        return response;
    }
}
