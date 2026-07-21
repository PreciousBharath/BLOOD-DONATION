package com.bloodbank.controller;

import com.bloodbank.model.Donor;
import com.bloodbank.service.DonorService;
import com.bloodbank.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/donor-register")
public class DonorRegisterServlet extends HttpServlet {
    private final DonorService donorService = new DonorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/donor-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String bloodGroup = req.getParameter("bloodGroup");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String password = req.getParameter("password");

        if (ValidationUtil.isBlank(name) || !ValidationUtil.isPositiveInt(age) || ValidationUtil.isBlank(gender) ||
                ValidationUtil.isBlank(bloodGroup) || !ValidationUtil.isValidPhone(phone) ||
                !ValidationUtil.isValidEmail(email) || ValidationUtil.isBlank(address) || ValidationUtil.isBlank(city) ||
                ValidationUtil.isBlank(password)) {
            req.setAttribute("error", "Please complete all donor fields with valid values.");
            req.getRequestDispatcher("/donor-register.jsp").forward(req, resp);
            return;
        }

        Donor donor = new Donor();
        donor.setName(name.trim());
        donor.setAge(Integer.parseInt(age.trim()));
        donor.setGender(gender.trim());
        donor.setBloodGroup(bloodGroup.trim().toUpperCase());
        donor.setPhone(phone.trim());
        donor.setEmail(email.trim().toLowerCase());
        donor.setAddress(address.trim());
        donor.setCity(city.trim());
        donor.setPassword(password);

        donorService.register(donor);
        resp.sendRedirect(req.getContextPath() + "/donor-login.jsp?success=registered");
    }
}
