<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donor Login</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="auth-card rounded-4 p-4 p-md-5">
                <%@ include file="/includes/alerts.jspf" %>
                <h3 class="fw-bold mb-1">Donor Login</h3>
                <p class="text-muted mb-4">Access notifications, requests, and donation history.</p>
                <form method="post" action="${pageContext.request.contextPath}/donor-login" class="row g-3">
                    <div class="col-12">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control form-control-lg" required>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control form-control-lg" required>
                    </div>
                    <div class="col-12">
                        <button class="btn btn-danger btn-lg w-100">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
