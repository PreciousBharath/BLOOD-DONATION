<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Request Status</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/patient-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4">
            <h3 class="fw-bold mb-0">Request Status</h3>
            <small class="text-muted">Track your blood requests and donor responses.</small>
        </div>
        <div class="surface-card p-3">
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead><tr><th>ID</th><th>Blood Group</th><th>Hospital</th><th>Status</th><th>Accepted Donors</th></tr></thead>
                    <tbody>
                    <c:forEach items="${requests}" var="request">
                        <tr>
                            <td>${request.id}</td>
                            <td>${request.bloodGroup}</td>
                            <td>${request.hospitalName}</td>
                            <td><span class="status-badge ${request.status eq 'APPROVED' ? 'status-approved' : request.status eq 'REJECTED' ? 'status-rejected' : 'status-pending'}">${request.status}</span></td>
                            <td><a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/view-accepted-donors?requestId=${request.id}">View Accepted Donors</a></td>
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
