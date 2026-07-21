<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donor Profile</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/donor-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4">
            <h3 class="fw-bold mb-0">Donor Profile</h3>
            <small class="text-muted">Keep donor information up to date.</small>
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="surface-card p-4">
            <form method="post" action="${pageContext.request.contextPath}/donor-profile" class="row g-3">
                <div class="col-md-6"><label class="form-label">Full Name</label><input name="name" class="form-control" value="${donor.name}" required></div>
                <div class="col-md-3"><label class="form-label">Age</label><input name="age" type="number" min="18" class="form-control" value="${donor.age}" required></div>
                <div class="col-md-3"><label class="form-label">Gender</label><input name="gender" class="form-control" value="${donor.gender}" required></div>
                <div class="col-md-4"><label class="form-label">Blood Group</label><input name="bloodGroup" class="form-control" value="${donor.bloodGroup}" required></div>
                <div class="col-md-4"><label class="form-label">Phone</label><input name="phone" class="form-control" value="${donor.phone}" required></div>
                <div class="col-md-4"><label class="form-label">Email</label><input name="email" type="email" class="form-control" value="${donor.email}" required></div>
                <div class="col-md-8"><label class="form-label">Address</label><input name="address" class="form-control" value="${donor.address}" required></div>
                <div class="col-md-4"><label class="form-label">City</label><input name="city" class="form-control" value="${donor.city}" required></div>
                <div class="col-12">
                    <button class="btn btn-danger">Update Profile</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
