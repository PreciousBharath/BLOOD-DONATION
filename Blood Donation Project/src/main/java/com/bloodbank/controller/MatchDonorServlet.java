package com.bloodbank.controller;

import com.bloodbank.model.BloodRequest;
import com.bloodbank.service.AdminService;
import com.bloodbank.service.BloodRequestService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/match-donor")
public class MatchDonorServlet extends HttpServlet {
    private final BloodRequestService bloodRequestService = new BloodRequestService();
    private final AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestId = req.getParameter("requestId");
        String action = req.getParameter("action");

        if (!ValidationUtil.isPositiveInt(requestId)) {
            resp.sendRedirect(req.getContextPath() + "/manage-requests?error=invalid");
            return;
        }

        long id = Long.parseLong(requestId);
        if ("reject".equalsIgnoreCase(action)) {
            bloodRequestService.reject(id);
            resp.sendRedirect(req.getContextPath() + "/manage-requests?success=request-rejected");
            return;
        }

        BloodRequest request = bloodRequestService.getById(id);
        if (request != null) {
            adminService.approveBloodRequest(id);
            adminService.matchDonorsForRequest(request);
        }
        resp.sendRedirect(req.getContextPath() + "/manage-requests?success=request-approved");
    }
}
