package com.bloodbank.controller;

import com.bloodbank.model.DonorResponse;
import com.bloodbank.service.ResponseService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view-accepted-donors")
public class ViewAcceptedDonorsServlet extends HttpServlet {
    private final ResponseService responseService = new ResponseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestId = req.getParameter("requestId");
        if (!ValidationUtil.isPositiveInt(requestId)) {
            req.setAttribute("error", "Invalid request selected.");
            req.getRequestDispatcher("/accepted-donors.jsp").forward(req, resp);
            return;
        }
        List<DonorResponse> acceptedDonors = responseService.listAcceptedDonors(Long.parseLong(requestId));
        req.setAttribute("acceptedDonors", acceptedDonors);
        req.getRequestDispatcher("/accepted-donors.jsp").forward(req, resp);
    }
}
