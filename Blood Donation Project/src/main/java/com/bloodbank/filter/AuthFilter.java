package com.bloodbank.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/",
            "/index.jsp",
            "/login.jsp",
            "/register.jsp",
            "/admin-login.jsp",
            "/donor-login.jsp",
            "/donor-register.jsp",
            "/patient-login.jsp",
            "/patient-register.jsp",
            "/admin-login",
            "/donor-login",
            "/donor-register",
            "/patient-login",
            "/patient-register",
            "/assets/",
            "/css/",
            "/js/",
            "/images/"
    );

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (isPublic(uri)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("userRole") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        String role = String.valueOf(session.getAttribute("userRole"));
        if (!isAuthorized(uri, role)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=unauthorized");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean isPublic(String uri) {
        if (uri == null || uri.isEmpty() || "/".equals(uri)) {
            return true;
        }
        for (String path : PUBLIC_PATHS) {
            if ("/".equals(path)) {
                continue;
            }
            if (uri.equals(path) || uri.startsWith(path)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuthorized(String uri, String role) {
        if (role == null) {
            return false;
        }
        if (uri.startsWith("/admin") || uri.startsWith("/manage-") || uri.startsWith("/reports")
                || uri.startsWith("/approve-") || uri.startsWith("/reject-") || uri.startsWith("/match-")) {
            return "ADMIN".equals(role);
        }
        if (uri.startsWith("/donor") || uri.startsWith("/accept-") || uri.startsWith("/reject-donation")) {
            return "DONOR".equals(role) || "ADMIN".equals(role);
        }
        if (uri.startsWith("/patient") || uri.startsWith("/create-") || uri.startsWith("/view-accepted-donors") || uri.startsWith("/request-status")) {
            return "PATIENT".equals(role) || "ADMIN".equals(role);
        }
        return true;
    }
}
