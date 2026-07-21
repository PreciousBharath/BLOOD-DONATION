package com.bloodbank.controller;

import com.bloodbank.service.AdminService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/approve-patient")
public class ApprovePatientServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String patientId = req.getParameter("patientId");
        if (!ValidationUtil.isPositiveInt(patientId)) {
            resp.sendRedirect(req.getContextPath() + "/manage-patients?error=invalid");
            return;
        }
        adminService.approvePatient(Long.parseLong(patientId));
        resp.sendRedirect(req.getContextPath() + "/manage-patients?success=patient-approved");
    }
}
