package com.bloodbank.service;

import com.bloodbank.dao.NotificationDao;
import com.bloodbank.model.DonorNotification;

import java.util.List;

public class NotificationService {
    private final NotificationDao notificationDao = new NotificationDao();

    public List<DonorNotification> listByDonor(long donorId) {
        return notificationDao.listByDonor(donorId);
    }
}
