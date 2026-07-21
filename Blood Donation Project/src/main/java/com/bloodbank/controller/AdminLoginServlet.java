package com.bloodbank.controller;

import com.bloodbank.model.Admin;
import com.bloodbank.service.AdminService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (ValidationUtil.isBlank(username) || ValidationUtil.isBlank(password)) {
            req.setAttribute("error", "Username and password are required.");
            req.getRequestDispatcher("/admin-login.jsp").forward(req, resp);
            return;
        }

        Admin admin = adminService.login(username.trim(), password);
        if (admin == null) {
            req.setAttribute("error", "Invalid admin credentials.");
            req.getRequestDispatcher("/admin-login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", admin.getId());
        session.setAttribute("userName", admin.getUsername());
        session.setAttribute("userRole", "ADMIN");
        resp.sendRedirect(req.getContextPath() + "/admin-dashboard");
    }
}
