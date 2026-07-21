<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Blood Donation Management System</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="container py-4 hero-shell">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <h1 class="fw-bold mb-1">Blood Donation Management System</h1>
            <p class="text-muted mb-0">Donor-patient matching with verification, notifications, and tracking.</p>
        </div>
        <div class="d-flex gap-2">
            <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/login.jsp">Login</a>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/register.jsp">Register</a>
        </div>
    </div>
    <div class="hero-banner mb-4">
        <div class="row align-items-center g-4 position-relative">
            <div class="col-lg-7">
                <span class="badge bg-light text-danger mb-3">Hospital-ready workflow</span>
                <h2 class="display-6 fw-bold">Connect approved donors to urgent blood requests faster.</h2>
                <p class="lead mb-4">Admin verification, donor notifications, response tracking, and accepted donor contact details are all handled in one MVC application.</p>
                <div class="d-flex gap-2 flex-wrap">
                    <a class="btn btn-light btn-lg" href="${pageContext.request.contextPath}/donor-register.jsp">Donor Registration</a>
                    <a class="btn btn-outline-light btn-lg" href="${pageContext.request.contextPath}/patient-register.jsp">Patient Registration</a>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="row g-3">
                    <div class="col-6">
                        <div class="surface-card p-3 h-100">
                            <div class="stat-icon mb-2">D</div>
                            <div class="fw-semibold">Donor Verification</div>
                            <small class="text-muted">Pending to approved workflow</small>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="surface-card p-3 h-100">
                            <div class="stat-icon mb-2">P</div>
                            <div class="fw-semibold">Patient Requests</div>
                            <small class="text-muted">Track and match requests</small>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="surface-card p-3">
                            <div class="fw-semibold">Security built-in</div>
                            <small class="text-muted">Password hashing, session control, role-based access, and prepared statements.</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row g-4">
        <div class="col-md-4">
            <div class="surface-card p-4 h-100">
                <h5>Admin</h5>
                <p class="text-muted">Verify donors, manage patients, approve requests, and review reports.</p>
                <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/admin-login.jsp">Admin Login</a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="surface-card p-4 h-100">
                <h5>Donor</h5>
                <p class="text-muted">Register, accept or reject matching requests, and maintain your profile.</p>
                <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/donor-login.jsp">Donor Login</a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="surface-card p-4 h-100">
                <h5>Patient</h5>
                <p class="text-muted">Create blood requests, track status, and view accepted donor contacts.</p>
                <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/patient-login.jsp">Patient Login</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
