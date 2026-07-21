<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manage Donors</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/admin-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4 d-flex justify-content-between align-items-center">
            <div>
                <h3 class="fw-bold mb-0">Manage Donors</h3>
                <small class="text-muted">Search, approve, and reject donor registrations.</small>
            </div>
            <input type="search" class="form-control w-auto" placeholder="Search donors" data-table-search="donorTable">
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="surface-card p-3">
            <div class="table-responsive">
                <table class="table align-middle" id="donorTable">
                    <thead>
                    <tr>
                        <th>Name</th><th>Blood Group</th><th>Phone</th><th>City</th><th>Status</th><th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${donors}" var="donor">
                        <tr>
                            <td>${donor.name}</td>
                            <td>${donor.bloodGroup}</td>
                            <td>${donor.phone}</td>
                            <td>${donor.city}</td>
                            <td>
                                <span class="status-badge ${donor.status eq 'APPROVED' ? 'status-approved' : donor.status eq 'REJECTED' ? 'status-rejected' : 'status-pending'}">${donor.status}</span>
                            </td>
                            <td class="d-flex gap-2 flex-wrap">
                                <form method="post" action="${pageContext.request.contextPath}/approve-donor">
                                    <input type="hidden" name="donorId" value="${donor.id}">
                                    <button class="btn btn-sm btn-success">Approve</button>
                                </form>
                                <form method="post" action="${pageContext.request.contextPath}/reject-donor">
                                    <input type="hidden" name="donorId" value="${donor.id}">
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
