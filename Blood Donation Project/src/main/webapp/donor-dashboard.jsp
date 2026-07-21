<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donor Dashboard</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/donor-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar d-flex justify-content-between align-items-center mb-4">
            <div>
                <h3 class="mb-0 fw-bold">Welcome, ${sessionScope.userName}</h3>
                <small class="text-muted">Respond to notifications and review your donation history.</small>
            </div>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="row g-3 mb-4">
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Pending Requests</div><div class="fs-3 fw-bold">${pendingCount}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Accepted Requests</div><div class="fs-3 fw-bold">${acceptedCount}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Rejected Requests</div><div class="fs-3 fw-bold">${rejectedCount}</div></div></div>
            <div class="col-md-3"><div class="stat-card p-3"><div class="text-muted">Donation History</div><div class="fs-3 fw-bold">${fn:length(history)}</div></div></div>
        </div>
        <div class="surface-card p-3 mb-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h5 class="mb-0">Notifications</h5>
                <a href="${pageContext.request.contextPath}/donor-notifications" class="btn btn-sm btn-outline-danger">Open full view</a>
            </div>
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead><tr><th>Request ID</th><th>Patient</th><th>Hospital</th><th>Status</th><th>Actions</th></tr></thead>
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
        <div class="surface-card p-3">
            <h5>Donation History</h5>
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead><tr><th>Request ID</th><th>Response</th><th>Date</th><th>Donor</th></tr></thead>
                    <tbody>
                    <c:forEach items="${history}" var="item">
                        <tr>
                            <td>${item.requestId}</td>
                            <td><span class="status-badge ${item.response eq 'ACCEPTED' ? 'status-approved' : 'status-rejected'}">${item.response}</span></td>
                            <td>${item.responseDate}</td>
                            <td>${item.donorName}</td>
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
