package com.bloodbank.controller;

import com.bloodbank.service.DonorService;
import com.bloodbank.util.ValidationUtil;
import com.bloodbank.model.Donor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/accept-donation")
public class AcceptDonationServlet extends HttpServlet {
    private final DonorService donorService = new DonorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String requestId = req.getParameter("requestId");
        if (session == null || !ValidationUtil.isPositiveInt(requestId)) {
            resp.sendRedirect(req.getContextPath() + "/donor-notifications.jsp?error=invalid");
            return;
        }
        long donorId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        Donor donor = donorService.getById(donorId);
        if (donor == null || !"APPROVED".equalsIgnoreCase(donor.getStatus())) {
            resp.sendRedirect(req.getContextPath() + "/donor-dashboard?error=donor-not-approved");
            return;
        }
        donorService.acceptRequest(Long.parseLong(requestId), donorId);
        resp.sendRedirect(req.getContextPath() + "/donor-dashboard?success=request-accepted");
    }
}
