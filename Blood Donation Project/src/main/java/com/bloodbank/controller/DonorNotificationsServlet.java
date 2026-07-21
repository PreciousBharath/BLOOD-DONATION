package com.bloodbank.controller;

import com.bloodbank.service.DonorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/donor-notifications")
public class DonorNotificationsServlet extends HttpServlet {
    private final DonorService donorService = new DonorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        long donorId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        req.setAttribute("notifications", donorService.getNotifications(donorId));
        req.getRequestDispatcher("/donor-notifications.jsp").forward(req, resp);
    }
}
