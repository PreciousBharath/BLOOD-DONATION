package com.bloodbank.service;

import com.bloodbank.dao.ResponseDao;
import com.bloodbank.model.DonorResponse;

import java.util.List;

public class ResponseService {
    private final ResponseDao responseDao = new ResponseDao();

    public List<DonorResponse> listAcceptedDonors(long requestId) {
        return responseDao.listAcceptedDonorsByRequest(requestId);
    }
}
