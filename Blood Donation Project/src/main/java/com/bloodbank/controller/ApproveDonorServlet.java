package com.bloodbank.controller;

import com.bloodbank.service.AdminService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/approve-donor")
public class ApproveDonorServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String donorId = req.getParameter("donorId");
        if (!ValidationUtil.isPositiveInt(donorId)) {
            resp.sendRedirect(req.getContextPath() + "/manage-donors?error=invalid");
            return;
        }
        adminService.approveDonor(Long.parseLong(donorId));
        resp.sendRedirect(req.getContextPath() + "/manage-donors?success=donor-approved");
    }
}
