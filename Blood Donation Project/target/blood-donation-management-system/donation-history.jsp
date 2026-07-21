<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donation History</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/donor-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4">
            <h3 class="fw-bold mb-0">Donation History</h3>
            <small class="text-muted">Accepted and rejected responses for your requests.</small>
        </div>
        <div class="surface-card p-3">
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
