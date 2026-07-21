<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login | Blood Donation Management System</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="auth-card rounded-4 p-4 p-md-5">
                <h2 class="fw-bold mb-2">Choose login portal</h2>
                <p class="text-muted mb-4">Use the portal that matches your role.</p>
                <div class="row g-3">
                    <div class="col-md-4">
                        <div class="surface-card p-4 h-100">
                            <h5>Admin</h5>
                            <p class="text-muted">Verify registrations and manage requests.</p>
                            <a class="btn btn-danger w-100" href="${pageContext.request.contextPath}/admin-login.jsp">Open</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="surface-card p-4 h-100">
                            <h5>Donor</h5>
                            <p class="text-muted">See notifications and respond to requests.</p>
                            <a class="btn btn-danger w-100" href="${pageContext.request.contextPath}/donor-login.jsp">Open</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="surface-card p-4 h-100">
                            <h5>Patient</h5>
                            <p class="text-muted">Submit blood requests and track status.</p>
                            <a class="btn btn-danger w-100" href="${pageContext.request.contextPath}/patient-login.jsp">Open</a>
                        </div>
                    </div>
                </div>
                <div class="mt-4">
                    <a href="${pageContext.request.contextPath}/index.jsp">Back to home</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
