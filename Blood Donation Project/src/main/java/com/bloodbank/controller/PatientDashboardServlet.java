package com.bloodbank.controller;

import com.bloodbank.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/patient-dashboard")
public class PatientDashboardServlet extends HttpServlet {
    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        long patientId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        req.setAttribute("requests", patientService.listRequestsByPatient(patientId));
        req.setAttribute("patient", patientService.getById(patientId));
        req.getRequestDispatcher("/patient-dashboard.jsp").forward(req, resp);
    }
}
