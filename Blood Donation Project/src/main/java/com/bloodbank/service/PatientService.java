package com.bloodbank.service;

import com.bloodbank.dao.BloodRequestDao;
import com.bloodbank.dao.PatientDao;
import com.bloodbank.dao.ResponseDao;
import com.bloodbank.model.BloodRequest;
import com.bloodbank.model.DonorResponse;
import com.bloodbank.model.Patient;
import com.bloodbank.util.PasswordUtil;

import java.util.List;

public class PatientService {
    private final PatientDao patientDao = new PatientDao();
    private final BloodRequestDao bloodRequestDao = new BloodRequestDao();
    private final ResponseDao responseDao = new ResponseDao();

    public long register(Patient patient) {
        patient.setPassword(PasswordUtil.hash(patient.getPassword()));
        patient.setStatus("PENDING");
        return patientDao.insert(patient);
    }

    public Patient login(String email, String password) {
        return patientDao.authenticate(email, password);
    }

    public Patient getById(long id) {
        return patientDao.findById(id);
    }

    public long createBloodRequest(BloodRequest request) {
        request.setStatus("PENDING");
        return bloodRequestDao.insert(request);
    }

    public List<BloodRequest> listRequestsByPatient(long patientId) {
        return bloodRequestDao.listByPatient(patientId);
    }

    public List<DonorResponse> getAcceptedDonors(long requestId) {
        return responseDao.listAcceptedDonorsByRequest(requestId);
    }
}
