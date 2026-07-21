package com.bloodbank.controller;

import com.bloodbank.model.Patient;
import com.bloodbank.service.PatientService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patient-register")
public class PatientRegisterServlet extends HttpServlet {
    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/patient-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String password = req.getParameter("password");

        if (ValidationUtil.isBlank(name) || !ValidationUtil.isValidPhone(phone) || !ValidationUtil.isValidEmail(email) ||
                ValidationUtil.isBlank(address) || ValidationUtil.isBlank(password)) {
            req.setAttribute("error", "Please complete all patient fields with valid values.");
            req.getRequestDispatcher("/patient-register.jsp").forward(req, resp);
            return;
        }

        Patient patient = new Patient();
        patient.setName(name.trim());
        patient.setPhone(phone.trim());
        patient.setEmail(email.trim().toLowerCase());
        patient.setAddress(address.trim());
        patient.setPassword(password);
        patientService.register(patient);
        resp.sendRedirect(req.getContextPath() + "/patient-login.jsp?success=registered");
    }
}
