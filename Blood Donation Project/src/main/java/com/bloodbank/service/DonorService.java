package com.bloodbank.service;

import com.bloodbank.dao.DonorDao;
import com.bloodbank.dao.NotificationDao;
import com.bloodbank.dao.ResponseDao;
import com.bloodbank.model.Donor;
import com.bloodbank.model.DonorNotification;
import com.bloodbank.model.DonorResponse;
import com.bloodbank.util.PasswordUtil;

import java.util.List;

public class DonorService {
    private final DonorDao donorDao = new DonorDao();
    private final NotificationDao notificationDao = new NotificationDao();
    private final ResponseDao responseDao = new ResponseDao();

    public long register(Donor donor) {
        donor.setPassword(PasswordUtil.hash(donor.getPassword()));
        donor.setStatus("PENDING");
        return donorDao.insert(donor);
    }

    public Donor login(String email, String password) {
        return donorDao.authenticate(email, password);
    }

    public Donor getById(long id) {
        return donorDao.findById(id);
    }

    public boolean updateProfile(Donor donor) {
        return donorDao.updateProfile(donor);
    }

    public List<DonorNotification> getNotifications(long donorId) {
        return notificationDao.listByDonor(donorId);
    }

    public List<DonorResponse> getDonationHistory(long donorId) {
        return responseDao.listByDonor(donorId);
    }

    public boolean acceptRequest(long requestId, long donorId) {
        notificationDao.updateStatus(requestId, donorId, "ACCEPTED");
        return responseDao.saveOrUpdate(requestId, donorId, "ACCEPTED");
    }

    public boolean rejectRequest(long requestId, long donorId) {
        notificationDao.updateStatus(requestId, donorId, "REJECTED");
        return responseDao.saveOrUpdate(requestId, donorId, "REJECTED");
    }
}
