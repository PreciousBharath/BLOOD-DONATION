package com.bloodbank.controller;

import com.bloodbank.model.Patient;
import com.bloodbank.service.PatientService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/patient-login")
public class PatientLoginServlet extends HttpServlet {
    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/patient-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!ValidationUtil.isValidEmail(email) || ValidationUtil.isBlank(password)) {
            req.setAttribute("error", "Valid email and password are required.");
            req.getRequestDispatcher("/patient-login.jsp").forward(req, resp);
            return;
        }

        Patient patient = patientService.login(email.trim().toLowerCase(), password);
        if (patient == null) {
            req.setAttribute("error", "Invalid patient credentials.");
            req.getRequestDispatcher("/patient-login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", patient.getId());
        session.setAttribute("userName", patient.getName());
        session.setAttribute("userRole", "PATIENT");
        resp.sendRedirect(req.getContextPath() + "/patient-dashboard");
    }
}
