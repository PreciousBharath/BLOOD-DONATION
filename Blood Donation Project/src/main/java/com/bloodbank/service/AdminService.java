package com.bloodbank.service;

import com.bloodbank.dao.AdminDao;
import com.bloodbank.dao.BloodRequestDao;
import com.bloodbank.dao.DonorDao;
import com.bloodbank.dao.NotificationDao;
import com.bloodbank.dao.PatientDao;
import com.bloodbank.dao.ResponseDao;
import com.bloodbank.model.Admin;
import com.bloodbank.model.BloodRequest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdminService {
    private final AdminDao adminDao = new AdminDao();
    private final DonorDao donorDao = new DonorDao();
    private final PatientDao patientDao = new PatientDao();
    private final BloodRequestDao bloodRequestDao = new BloodRequestDao();
    private final NotificationDao notificationDao = new NotificationDao();
    private final ResponseDao responseDao = new ResponseDao();

    public Admin login(String username, String password) {
        return adminDao.authenticate(username, password);
    }

    public boolean approveDonor(long donorId) {
        return donorDao.updateStatus(donorId, "APPROVED");
    }

    public boolean rejectDonor(long donorId) {
        return donorDao.updateStatus(donorId, "REJECTED");
    }

    public boolean approvePatient(long patientId) {
        return patientDao.updateStatus(patientId, "APPROVED");
    }

    public boolean rejectPatient(long patientId) {
        return patientDao.updateStatus(patientId, "REJECTED");
    }

    public boolean approveBloodRequest(long requestId) {
        return bloodRequestDao.updateStatus(requestId, "APPROVED");
    }

    public boolean rejectBloodRequest(long requestId) {
        return bloodRequestDao.updateStatus(requestId, "REJECTED");
    }

    public Map<String, Long> getReportCounts() {
        Map<String, Long> counts = new LinkedHashMap<>();
        counts.put("pendingDonors", donorDao.countByStatus("PENDING"));
        counts.put("approvedDonors", donorDao.countByStatus("APPROVED"));
        counts.put("pendingPatients", patientDao.countByStatus("PENDING"));
        counts.put("approvedPatients", patientDao.countByStatus("APPROVED"));
        counts.put("pendingRequests", bloodRequestDao.countByStatus("PENDING"));
        counts.put("approvedRequests", bloodRequestDao.countByStatus("APPROVED"));
        counts.put("rejectedRequests", bloodRequestDao.countByStatus("REJECTED"));
        return counts;
    }

    public List<com.bloodbank.model.Donor> listDonors() {
        return donorDao.listAll();
    }

    public List<com.bloodbank.model.Donor> listPendingDonors() {
        return donorDao.listByStatus("PENDING");
    }

    public List<com.bloodbank.model.Donor> listApprovedDonors() {
        return donorDao.listByStatus("APPROVED");
    }

    public List<com.bloodbank.model.Patient> listPatients() {
        return patientDao.listAll();
    }

    public List<com.bloodbank.model.Patient> listPendingPatients() {
        return patientDao.listByStatus("PENDING");
    }

    public List<com.bloodbank.model.Patient> listApprovedPatients() {
        return patientDao.listByStatus("APPROVED");
    }

    public List<BloodRequest> listRequests() {
        return bloodRequestDao.listAll();
    }

    public List<BloodRequest> listPendingRequests() {
        return bloodRequestDao.listByStatus("PENDING");
    }

    public void matchDonorsForRequest(BloodRequest request) {
        if (request == null) {
            return;
        }
        List<Long> donorIds = notificationDao.listDonorIdsByRequestAndMatchingBloodGroup(request.getBloodGroup());
        for (Long donorId : donorIds) {
            notificationDao.create(request.getId(), donorId);
        }
    }
}
