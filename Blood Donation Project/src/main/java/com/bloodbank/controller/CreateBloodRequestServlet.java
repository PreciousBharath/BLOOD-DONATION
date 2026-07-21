package com.bloodbank.controller;

import com.bloodbank.model.BloodRequest;
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
import java.time.LocalDate;

@WebServlet("/create-blood-request")
public class CreateBloodRequestServlet extends HttpServlet {
    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create-request.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        long patientId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        Patient patient = patientService.getById(patientId);
        if (patient == null || !"APPROVED".equalsIgnoreCase(patient.getStatus())) {
            req.setAttribute("error", "Your patient account must be approved by admin before raising requests.");
            req.getRequestDispatcher("/create-request.jsp").forward(req, resp);
            return;
        }

        String bloodGroup = req.getParameter("bloodGroup");
        String quantity = req.getParameter("quantity");
        String hospitalName = req.getParameter("hospitalName");
        String hospitalLocation = req.getParameter("hospitalLocation");
        String doctorDescription = req.getParameter("doctorDescription");
        String emergencyLevel = req.getParameter("emergencyLevel");
        String requiredDate = req.getParameter("requiredDate");

        if (ValidationUtil.isBlank(bloodGroup) || !ValidationUtil.isPositiveInt(quantity) ||
                ValidationUtil.isBlank(hospitalName) || ValidationUtil.isBlank(hospitalLocation) ||
                ValidationUtil.isBlank(doctorDescription) || ValidationUtil.isBlank(emergencyLevel) ||
                ValidationUtil.isBlank(requiredDate)) {
            req.setAttribute("error", "Complete all blood request fields.");
            req.getRequestDispatcher("/create-request.jsp").forward(req, resp);
            return;
        }

        BloodRequest request = new BloodRequest();
        request.setPatientId(patientId);
        request.setBloodGroup(bloodGroup.trim().toUpperCase());
        request.setQuantity(Integer.parseInt(quantity.trim()));
        request.setHospitalName(hospitalName.trim());
        request.setHospitalLocation(hospitalLocation.trim());
        request.setDoctorDescription(doctorDescription.trim());
        request.setEmergencyLevel(emergencyLevel.trim());
        request.setRequiredDate(LocalDate.parse(requiredDate.trim()));
        patientService.createBloodRequest(request);
        resp.sendRedirect(req.getContextPath() + "/request-status?success=request-created");
    }
}
