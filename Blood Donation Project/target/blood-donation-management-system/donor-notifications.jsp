<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donor Notifications</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/donor-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4">
            <h3 class="fw-bold mb-0">Donor Notifications</h3>
            <small class="text-muted">Incoming blood request alerts for approved donors.</small>
        </div>
        <div class="surface-card p-3">
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead><tr><th>Request ID</th><th>Patient</th><th>Hospital</th><th>Status</th><th>Action</th></tr></thead>
                    <tbody>
                    <c:forEach items="${notifications}" var="notification">
                        <tr>
                            <td>${notification.requestId}</td>
                            <td>${notification.patientName}</td>
                            <td>${notification.hospitalName}</td>
                            <td><span class="status-badge ${notification.notificationStatus eq 'ACCEPTED' ? 'status-approved' : notification.notificationStatus eq 'REJECTED' ? 'status-rejected' : 'status-unread'}">${notification.notificationStatus}</span></td>
                            <td class="d-flex gap-2 flex-wrap">
                                <form method="post" action="${pageContext.request.contextPath}/accept-donation">
                                    <input type="hidden" name="requestId" value="${notification.requestId}">
                                    <button class="btn btn-sm btn-success">Accept</button>
                                </form>
                                <form method="post" action="${pageContext.request.contextPath}/reject-donation">
                                    <input type="hidden" name="requestId" value="${notification.requestId}">
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
