<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Reports</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/admin-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4">
            <h3 class="fw-bold mb-0">Reports</h3>
            <small class="text-muted">Operational counts across donors, patients, and requests.</small>
        </div>
        <div class="row g-3">
            <div class="col-md-3"><div class="stat-card p-4"><div class="text-muted">Pending Donors</div><div class="fs-2 fw-bold">${reports.pendingDonors}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-4"><div class="text-muted">Approved Donors</div><div class="fs-2 fw-bold">${reports.approvedDonors}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-4"><div class="text-muted">Pending Patients</div><div class="fs-2 fw-bold">${reports.pendingPatients}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-4"><div class="text-muted">Approved Patients</div><div class="fs-2 fw-bold">${reports.approvedPatients}</div></div></div>
            <div class="col-md-4"><div class="stat-card p-4"><div class="text-muted">Pending Requests</div><div class="fs-2 fw-bold">${reports.pendingRequests}</div></div></div>
            <div class="col-md-4"><div class="stat-card p-4"><div class="text-muted">Approved Requests</div><div class="fs-2 fw-bold">${reports.approvedRequests}</div></div></div>
            <div class="col-md-4"><div class="stat-card p-4"><div class="text-muted">Rejected Requests</div><div class="fs-2 fw-bold">${reports.rejectedRequests}</div></div></div>
        </div>
    </main>
</div>
</body>
</html>
