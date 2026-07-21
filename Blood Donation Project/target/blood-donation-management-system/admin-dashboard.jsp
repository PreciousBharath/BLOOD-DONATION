<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Dashboard</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/admin-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar d-flex justify-content-between align-items-center mb-4">
            <div>
                <h3 class="mb-0 fw-bold">Admin Dashboard</h3>
                <small class="text-muted">Monitor registrations, approvals, and requests.</small>
            </div>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <c:set var="reports" value="${reports}" />
        <div class="row g-3 mb-4">
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Pending Donors</div><div class="fs-3 fw-bold">${reports.pendingDonors}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Approved Donors</div><div class="fs-3 fw-bold">${reports.approvedDonors}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Pending Patients</div><div class="fs-3 fw-bold">${reports.pendingPatients}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Pending Requests</div><div class="fs-3 fw-bold">${reports.pendingRequests}</div></div></div>
        </div>
        <div class="row g-4">
            <div class="col-lg-4">
                <div class="surface-card p-3 h-100">
                    <h5>Pending Donors</h5>
                    <div class="table-responsive">
                        <table class="table table-sm align-middle">
                            <thead><tr><th>Name</th><th>Status</th></tr></thead>
                            <tbody>
                            <c:forEach items="${pendingDonors}" var="donor">
                                <tr>
                                    <td>${donor.name}</td>
                                    <td><span class="status-badge status-pending">${donor.status}</span></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="surface-card p-3 h-100">
                    <h5>Pending Patients</h5>
                    <div class="table-responsive">
                        <table class="table table-sm align-middle">
                            <thead><tr><th>Name</th><th>Status</th></tr></thead>
                            <tbody>
                            <c:forEach items="${pendingPatients}" var="patient">
                                <tr>
                                    <td>${patient.name}</td>
                                    <td><span class="status-badge status-pending">${patient.status}</span></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="surface-card p-3 h-100">
                    <h5>Pending Requests</h5>
                    <div class="table-responsive">
                        <table class="table table-sm align-middle">
                            <thead><tr><th>Blood Group</th><th>Status</th></tr></thead>
                            <tbody>
                            <c:forEach items="${pendingRequests}" var="request">
                                <tr>
                                    <td>${request.bloodGroup}</td>
                                    <td><span class="status-badge status-pending">${request.status}</span></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
