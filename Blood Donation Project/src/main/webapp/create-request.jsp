<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Blood Request</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/patient-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4">
            <h3 class="fw-bold mb-0">Create Blood Request</h3>
            <small class="text-muted">Requests start in pending status until approved by admin.</small>
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="surface-card p-4">
            <form method="post" action="${pageContext.request.contextPath}/create-blood-request" class="row g-3">
                <div class="col-md-4"><label class="form-label">Blood Group Needed</label><select name="bloodGroup" class="form-select" required><option>A+</option><option>A-</option><option>B+</option><option>B-</option><option>AB+</option><option>AB-</option><option>O+</option><option>O-</option></select></div>
                <div class="col-md-4"><label class="form-label">Quantity Required</label><input name="quantity" type="number" min="1" class="form-control" required></div>
                <div class="col-md-4"><label class="form-label">Required Date</label><input name="requiredDate" type="date" class="form-control" required></div>
                <div class="col-md-6"><label class="form-label">Hospital Name</label><input name="hospitalName" class="form-control" required></div>
                <div class="col-md-6"><label class="form-label">Hospital Location</label><input name="hospitalLocation" class="form-control" required></div>
                <div class="col-12"><label class="form-label">Doctor Description</label><textarea name="doctorDescription" rows="4" class="form-control" required></textarea></div>
                <div class="col-md-4"><label class="form-label">Emergency Level</label><select name="emergencyLevel" class="form-select" required><option>Low</option><option>Medium</option><option>High</option><option>Critical</option></select></div>
                <div class="col-12">
                    <button class="btn btn-danger">Submit Request</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
