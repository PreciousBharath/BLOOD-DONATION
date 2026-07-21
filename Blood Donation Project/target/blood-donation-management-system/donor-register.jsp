<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donor Registration</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="auth-card rounded-4 p-4 p-md-5">
                <%@ include file="/includes/alerts.jspf" %>
                <h3 class="fw-bold mb-1">Donor Registration</h3>
                <p class="text-muted mb-4">New donors are stored with <span class="fw-semibold">PENDING</span> status until verified by admin.</p>
                <form method="post" action="${pageContext.request.contextPath}/donor-register" class="row g-3">
                    <div class="col-md-6"><label class="form-label">Full Name</label><input name="name" class="form-control" required></div>
                    <div class="col-md-3"><label class="form-label">Age</label><input name="age" type="number" min="18" class="form-control" required></div>
                    <div class="col-md-3"><label class="form-label">Gender</label><select name="gender" class="form-select" required><option>Male</option><option>Female</option><option>Other</option></select></div>
                    <div class="col-md-4"><label class="form-label">Blood Group</label><select name="bloodGroup" class="form-select" required><option>A+</option><option>A-</option><option>B+</option><option>B-</option><option>AB+</option><option>AB-</option><option>O+</option><option>O-</option></select></div>
                    <div class="col-md-4"><label class="form-label">Phone Number</label><input name="phone" class="form-control" required></div>
                    <div class="col-md-4"><label class="form-label">Email</label><input name="email" type="email" class="form-control" required></div>
                    <div class="col-md-6"><label class="form-label">Address</label><input name="address" class="form-control" required></div>
                    <div class="col-md-3"><label class="form-label">City</label><input name="city" class="form-control" required></div>
                    <div class="col-md-3"><label class="form-label">Password</label><input name="password" type="password" class="form-control" required></div>
                    <div class="col-12">
                        <button class="btn btn-danger">Create Account</button>
                        <a class="btn btn-link" href="${pageContext.request.contextPath}/donor-login.jsp">Already registered?</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
