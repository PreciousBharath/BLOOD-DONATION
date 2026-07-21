package com.bloodbank.dao;

import com.bloodbank.model.Donor;
import com.bloodbank.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DonorDao {
    public long insert(Donor donor) {
        String sql = "INSERT INTO donor(name, age, gender, blood_group, phone, email, address, city, password, status) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, donor.getName());
            statement.setInt(2, donor.getAge());
            statement.setString(3, donor.getGender());
            statement.setString(4, donor.getBloodGroup());
            statement.setString(5, donor.getPhone());
            statement.setString(6, donor.getEmail());
            statement.setString(7, donor.getAddress());
            statement.setString(8, donor.getCity());
            statement.setString(9, donor.getPassword());
            statement.setString(10, donor.getStatus());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getLong(1) : 0L;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to insert donor", exception);
        }
    }

    public Donor findById(long id) {
        String sql = "SELECT * FROM donor WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return map(resultSet);
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to load donor", exception);
        }
    }

    public Donor findByEmail(String email) {
        String sql = "SELECT * FROM donor WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return map(resultSet);
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to load donor by email", exception);
        }
    }

    public Donor authenticate(String email, String password) {
        Donor donor = findByEmail(email);
        if (donor != null && com.bloodbank.util.PasswordUtil.matches(password, donor.getPassword())) {
            return donor;
        }
        return null;
    }

    public boolean updateStatus(long id, String status) {
        String sql = "UPDATE donor SET status = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to update donor status", exception);
        }
    }

    public boolean updateProfile(Donor donor) {
        String sql = "UPDATE donor SET name=?, age=?, gender=?, blood_group=?, phone=?, email=?, address=?, city=? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, donor.getName());
            statement.setInt(2, donor.getAge());
            statement.setString(3, donor.getGender());
            statement.setString(4, donor.getBloodGroup());
            statement.setString(5, donor.getPhone());
            statement.setString(6, donor.getEmail());
            statement.setString(7, donor.getAddress());
            statement.setString(8, donor.getCity());
            statement.setLong(9, donor.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to update donor profile", exception);
        }
    }

    public List<Donor> listAll() {
        return list("SELECT * FROM donor ORDER BY created_at DESC");
    }

    public List<Donor> listByStatus(String status) {
        return list("SELECT * FROM donor WHERE status = ? ORDER BY created_at DESC", status);
    }

    public List<Donor> listApprovedByBloodGroup(String bloodGroup) {
        return list("SELECT * FROM donor WHERE blood_group = ? AND status = 'APPROVED' ORDER BY name", bloodGroup);
    }

    public long countByStatus(String status) {
        String sql = "SELECT COUNT(*) AS total FROM donor WHERE status = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getLong("total") : 0L;
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to count donors", exception);
        }
    }

    private List<Donor> list(String sql, Object... params) {
        List<Donor> donors = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 0; index < params.length; index++) {
                statement.setObject(index + 1, params[index]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    donors.add(mapRow(resultSet));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to list donors", exception);
        }
        return donors;
    }

    private Donor map(ResultSet resultSet) throws Exception {
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    private Donor mapRow(ResultSet resultSet) throws Exception {
        Donor donor = new Donor();
        donor.setId(resultSet.getLong("id"));
        donor.setName(resultSet.getString("name"));
        donor.setAge(resultSet.getInt("age"));
        donor.setGender(resultSet.getString("gender"));
        donor.setBloodGroup(resultSet.getString("blood_group"));
        donor.setPhone(resultSet.getString("phone"));
        donor.setEmail(resultSet.getString("email"));
        donor.setAddress(resultSet.getString("address"));
        donor.setCity(resultSet.getString("city"));
        donor.setPassword(resultSet.getString("password"));
        donor.setStatus(resultSet.getString("status"));
        java.sql.Timestamp timestamp = resultSet.getTimestamp("created_at");
        if (timestamp != null) {
            donor.setCreatedAt(timestamp.toLocalDateTime());
        }
        return donor;
    }
}
