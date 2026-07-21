<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manage Patients</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/admin-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4 d-flex justify-content-between align-items-center">
            <div>
                <h3 class="fw-bold mb-0">Manage Patients</h3>
                <small class="text-muted">Verify patient registrations before request creation.</small>
            </div>
            <input type="search" class="form-control w-auto" placeholder="Search patients" data-table-search="patientTable">
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="surface-card p-3">
            <div class="table-responsive">
                <table class="table align-middle" id="patientTable">
                    <thead>
                    <tr>
                        <th>Name</th><th>Phone</th><th>Email</th><th>Status</th><th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${patients}" var="patient">
                        <tr>
                            <td>${patient.name}</td>
                            <td>${patient.phone}</td>
                            <td>${patient.email}</td>
                            <td>
                                <span class="status-badge ${patient.status eq 'APPROVED' ? 'status-approved' : patient.status eq 'REJECTED' ? 'status-rejected' : 'status-pending'}">${patient.status}</span>
                            </td>
                            <td class="d-flex gap-2 flex-wrap">
                                <form method="post" action="${pageContext.request.contextPath}/approve-patient">
                                    <input type="hidden" name="patientId" value="${patient.id}">
                                    <button class="btn btn-sm btn-success">Approve</button>
                                </form>
                                <form method="post" action="${pageContext.request.contextPath}/reject-patient">
                                    <input type="hidden" name="patientId" value="${patient.id}">
                                    <button class="btn btn-sm btn-outline-danger">Reject</button>
                                </form>
                            </td>
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
