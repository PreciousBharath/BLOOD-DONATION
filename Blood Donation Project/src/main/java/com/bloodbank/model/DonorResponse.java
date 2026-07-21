package com.bloodbank.model;

import java.time.LocalDateTime;

public class DonorResponse {
    private long id;
    private long requestId;
    private long donorId;
    private String response;
    private LocalDateTime responseDate;
    private String donorName;
    private String phone;
    private String email;
    private String city;
    private String bloodGroup;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getRequestId() { return requestId; }
    public void setRequestId(long requestId) { this.requestId = requestId; }
    public long getDonorId() { return donorId; }
    public void setDonorId(long donorId) { this.donorId = donorId; }
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
    public LocalDateTime getResponseDate() { return responseDate; }
    public void setResponseDate(LocalDateTime responseDate) { this.responseDate = responseDate; }
    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
}
