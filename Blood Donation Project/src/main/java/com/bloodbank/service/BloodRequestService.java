package com.bloodbank.service;

import com.bloodbank.dao.BloodRequestDao;
import com.bloodbank.model.BloodRequest;

import java.util.List;

public class BloodRequestService {
    private final BloodRequestDao bloodRequestDao = new BloodRequestDao();
    private final AdminService adminService = new AdminService();

    public BloodRequest getById(long id) {
        return bloodRequestDao.findById(id);
    }

    public boolean approveAndMatch(long requestId) {
        boolean updated = bloodRequestDao.updateStatus(requestId, "APPROVED");
        if (updated) {
            adminService.matchDonorsForRequest(bloodRequestDao.findById(requestId));
        }
        return updated;
    }

    public boolean reject(long requestId) {
        return bloodRequestDao.updateStatus(requestId, "REJECTED");
    }

    public List<BloodRequest> listAll() {
        return bloodRequestDao.listAll();
    }

    public List<BloodRequest> listPending() {
        return bloodRequestDao.listByStatus("PENDING");
    }
}
