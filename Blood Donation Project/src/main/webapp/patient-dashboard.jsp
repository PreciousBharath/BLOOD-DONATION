<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Patient Dashboard</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/patient-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar d-flex justify-content-between align-items-center mb-4">
            <div>
                <h3 class="mb-0 fw-bold">Welcome, ${sessionScope.userName}</h3>
                <small class="text-muted">Create requests and track donor responses.</small>
            </div>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/create-blood-request">New Request</a>
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="row g-3 mb-4">
            <div class="col-md-4"><div class="stat-card p-3"><div class="text-muted">Requests</div><div class="fs-3 fw-bold">${fn:length(requests)}</div></div></div>
            <div class="col-md-4"><div class="stat-card p-3"><div class="text-muted">Account Status</div><div class="fs-3 fw-bold">${patient.status}</div></div></div>
            <div class="col-md-4"><div class="stat-card p-3"><div class="text-muted">Quick Action</div><div class="fs-3 fw-bold">Track</div></div></div>
        </div>
        <div class="surface-card p-3">
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead><tr><th>ID</th><th>Blood Group</th><th>Hospital</th><th>Required Date</th><th>Status</th><th>Accepted Donors</th></tr></thead>
                    <tbody>
                    <c:forEach items="${requests}" var="request">
                        <tr>
                            <td>${request.id}</td>
                            <td>${request.bloodGroup}</td>
                            <td>${request.hospitalName}</td>
                            <td>${request.requiredDate}</td>
                            <td><span class="status-badge ${request.status eq 'APPROVED' ? 'status-approved' : request.status eq 'REJECTED' ? 'status-rejected' : 'status-pending'}">${request.status}</span></td>
                            <td><a href="${pageContext.request.contextPath}/view-accepted-donors?requestId=${request.id}" class="btn btn-sm btn-outline-danger">View</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</div>
</body>
</html>
