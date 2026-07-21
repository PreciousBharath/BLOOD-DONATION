package com.bloodbank.controller;

import com.bloodbank.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reports", adminService.getReportCounts());
        req.setAttribute("pendingDonors", adminService.listPendingDonors());
        req.setAttribute("pendingPatients", adminService.listPendingPatients());
        req.setAttribute("pendingRequests", adminService.listPendingRequests());
        req.getRequestDispatcher("/admin-dashboard.jsp").forward(req, resp);
    }
}
