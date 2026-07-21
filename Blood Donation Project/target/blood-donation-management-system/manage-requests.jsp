<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manage Requests</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/admin-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4 d-flex justify-content-between align-items-center">
            <div>
                <h3 class="fw-bold mb-0">Manage Requests</h3>
                <small class="text-muted">Approve or reject requests, then match approved donors.</small>
            </div>
            <input type="search" class="form-control w-auto" placeholder="Search requests" data-table-search="requestTable">
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="surface-card p-3">
            <div class="table-responsive">
                <table class="table align-middle" id="requestTable">
                    <thead>
                    <tr>
                        <th>ID</th><th>Blood Group</th><th>Hospital</th><th>Emergency</th><th>Status</th><th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requests}" var="request">
                        <tr>
                            <td>${request.id}</td>
                            <td>${request.bloodGroup}</td>
                            <td>${request.hospitalName}</td>
                            <td>${request.emergencyLevel}</td>
                            <td>
                                <span class="status-badge ${request.status eq 'APPROVED' ? 'status-approved' : request.status eq 'REJECTED' ? 'status-rejected' : 'status-pending'}">${request.status}</span>
                            </td>
                            <td class="d-flex gap-2 flex-wrap">
                                <form method="post" action="${pageContext.request.contextPath}/match-donor">
                                    <input type="hidden" name="requestId" value="${request.id}">
                                    <input type="hidden" name="action" value="approve">
                                    <button class="btn btn-sm btn-success">Approve & Match</button>
                                </form>
                                <form method="post" action="${pageContext.request.contextPath}/match-donor">
                                    <input type="hidden" name="requestId" value="${request.id}">
                                    <input type="hidden" name="action" value="reject">
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
