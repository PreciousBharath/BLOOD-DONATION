package com.bloodbank.controller;

import com.bloodbank.service.DonorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/donor-dashboard")
public class DonorDashboardServlet extends HttpServlet {
    private final DonorService donorService = new DonorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        long donorId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        var notifications = donorService.getNotifications(donorId);
        long pendingCount = notifications.stream().filter(notification -> "UNREAD".equalsIgnoreCase(notification.getNotificationStatus())).count();
        long acceptedCount = notifications.stream().filter(notification -> "ACCEPTED".equalsIgnoreCase(notification.getNotificationStatus())).count();
        long rejectedCount = notifications.stream().filter(notification -> "REJECTED".equalsIgnoreCase(notification.getNotificationStatus())).count();
        req.setAttribute("notifications", notifications);
        req.setAttribute("pendingCount", pendingCount);
        req.setAttribute("acceptedCount", acceptedCount);
        req.setAttribute("rejectedCount", rejectedCount);
        req.setAttribute("history", donorService.getDonationHistory(donorId));
        req.setAttribute("donor", donorService.getById(donorId));
        req.getRequestDispatcher("/donor-dashboard.jsp").forward(req, resp);
    }
}
