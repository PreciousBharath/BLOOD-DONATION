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

@WebServlet("/donor-profile")
public class DonorProfileServlet extends HttpServlet {
    private final DonorService donorService = new DonorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        long donorId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        req.setAttribute("donor", donorService.getById(donorId));
        req.getRequestDispatcher("/donor-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        long donorId = Long.parseLong(String.valueOf(session.getAttribute("userId")));
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String bloodGroup = req.getParameter("bloodGroup");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String city = req.getParameter("city");

        if (ValidationUtil.isBlank(name) || !ValidationUtil.isPositiveInt(age) || ValidationUtil.isBlank(gender) ||
                ValidationUtil.isBlank(bloodGroup) || !ValidationUtil.isValidPhone(phone) ||
                !ValidationUtil.isValidEmail(email) || ValidationUtil.isBlank(address) || ValidationUtil.isBlank(city)) {
            req.setAttribute("error", "Please complete all donor profile fields.");
            doGet(req, resp);
            return;
        }

        Donor donor = new Donor();
        donor.setId(donorId);
        donor.setName(name.trim());
        donor.setAge(Integer.parseInt(age.trim()));
        donor.setGender(gender.trim());
        donor.setBloodGroup(bloodGroup.trim().toUpperCase());
        donor.setPhone(phone.trim());
        donor.setEmail(email.trim().toLowerCase());
        donor.setAddress(address.trim());
        donor.setCity(city.trim());
        donorService.updateProfile(donor);
        resp.sendRedirect(req.getContextPath() + "/donor-profile?success=updated");
    }
}
