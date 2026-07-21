package com.bloodbank.controller;

import com.bloodbank.service.DonorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/donation-history")
public class DonationHistoryServlet extends HttpServlet {
    private final DonorService donorService = new DonorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        long donorId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        req.setAttribute("history", donorService.getDonationHistory(donorId));
        req.getRequestDispatcher("/donation-history.jsp").forward(req, resp);
    }
}
