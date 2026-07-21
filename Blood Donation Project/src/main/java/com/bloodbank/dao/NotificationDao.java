package com.bloodbank.dao;

import com.bloodbank.model.DonorNotification;
import com.bloodbank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {
    public int create(long requestId, long donorId) {
        String sql = "INSERT INTO donor_notification(request_id, donor_id, notification_status) VALUES(?,?, 'UNREAD') ON DUPLICATE KEY UPDATE notification_status = notification_status";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, requestId);
            statement.setLong(2, donorId);
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException("Failed to create donor notification", exception);
        }
    }

    public boolean updateStatus(long requestId, long donorId, String status) {
        String sql = "UPDATE donor_notification SET notification_status = ? WHERE request_id = ? AND donor_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setLong(2, requestId);
            statement.setLong(3, donorId);
            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to update notification status", exception);
        }
    }

    public List<DonorNotification> listByDonor(long donorId) {
        String sql = """
                SELECT n.id, n.request_id, n.donor_id, n.notification_status,
                       d.name AS donor_name, d.blood_group,
                       p.name AS patient_name, r.hospital_name, r.emergency_level
                FROM donor_notification n
                JOIN donor d ON d.id = n.donor_id
                JOIN blood_request r ON r.id = n.request_id
                JOIN patient p ON p.id = r.patient_id
                WHERE n.donor_id = ?
                ORDER BY n.id DESC
                """;
        List<DonorNotification> notifications = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, donorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DonorNotification notification = new DonorNotification();
                    notification.setId(resultSet.getLong("id"));
                    notification.setRequestId(resultSet.getLong("request_id"));
                    notification.setDonorId(resultSet.getLong("donor_id"));
                    notification.setNotificationStatus(resultSet.getString("notification_status"));
                    notification.setDonorName(resultSet.getString("donor_name"));
                    notification.setBloodGroup(resultSet.getString("blood_group"));
                    notification.setPatientName(resultSet.getString("patient_name"));
                    notification.setHospitalName(resultSet.getString("hospital_name"));
                    notification.setEmergencyLevel(resultSet.getString("emergency_level"));
                    notifications.add(notification);
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to list donor notifications", exception);
        }
        return notifications;
    }

    public List<Long> listDonorIdsByRequestAndMatchingBloodGroup(String bloodGroup) {
        String sql = "SELECT id FROM donor WHERE blood_group = ? AND status = 'APPROVED'";
        List<Long> donorIds = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bloodGroup);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    donorIds.add(resultSet.getLong("id"));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to find matching donors", exception);
        }
        return donorIds;
    }
}
