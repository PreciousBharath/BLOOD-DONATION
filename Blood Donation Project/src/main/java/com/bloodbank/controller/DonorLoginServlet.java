package com.bloodbank.controller;

import com.bloodbank.model.Donor;
import com.bloodbank.service.DonorService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/donor-login")
public class DonorLoginServlet extends HttpServlet {
    private final DonorService donorService = new DonorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/donor-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!ValidationUtil.isValidEmail(email) || ValidationUtil.isBlank(password)) {
            req.setAttribute("error", "Valid email and password are required.");
            req.getRequestDispatcher("/donor-login.jsp").forward(req, resp);
            return;
        }

        Donor donor = donorService.login(email.trim().toLowerCase(), password);
        if (donor == null) {
            req.setAttribute("error", "Invalid donor credentials.");
            req.getRequestDispatcher("/donor-login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", donor.getId());
        session.setAttribute("userName", donor.getName());
        session.setAttribute("userRole", "DONOR");
        resp.sendRedirect(req.getContextPath() + "/donor-dashboard");
    }
}
