<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register | Blood Donation Management System</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="auth-card rounded-4 p-4 p-md-5">
                <h2 class="fw-bold mb-2">Choose registration portal</h2>
                <p class="text-muted mb-4">Donor and patient onboarding are separated by workflow.</p>
                <div class="row g-3">
                    <div class="col-md-6">
                        <div class="surface-card p-4 h-100">
                            <h5>Donor Registration</h5>
                            <p class="text-muted">Create a donor profile that starts in pending status.</p>
                            <a class="btn btn-outline-danger w-100" href="${pageContext.request.contextPath}/donor-register.jsp">Open</a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="surface-card p-4 h-100">
                            <h5>Patient Registration</h5>
                            <p class="text-muted">Register patients who can raise blood requests.</p>
                            <a class="btn btn-outline-danger w-100" href="${pageContext.request.contextPath}/patient-register.jsp">Open</a>
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
