<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Accepted Donors</title>
    <%@ include file="/includes/head.jspf" %>
</head>
<body>
<div class="app-shell d-flex">
    <%@ include file="/includes/patient-sidebar.jspf" %>
    <main class="content-wrap">
        <div class="topbar mb-4">
            <h3 class="fw-bold mb-0">Accepted Donors</h3>
            <small class="text-muted">Contact details for donors who accepted the request.</small>
        </div>
        <%@ include file="/includes/alerts.jspf" %>
        <div class="surface-card p-3">
            <div class="table-responsive">
                <table class="table align-middle">
                    <thead><tr><th>Name</th><th>Blood Group</th><th>Phone</th><th>Email</th><th>City</th><th>Response</th></tr></thead>
                    <tbody>
                    <c:forEach items="${acceptedDonors}" var="donor">
                        <tr>
                            <td>${donor.donorName}</td>
                            <td>${donor.bloodGroup}</td>
                            <td>${donor.phone}</td>
                            <td>${donor.email}</td>
                            <td>${donor.city}</td>
                            <td><span class="status-badge status-approved">${donor.response}</span></td>
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
