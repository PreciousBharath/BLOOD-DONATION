CREATE DATABASE IF NOT EXISTS blood_donation_db;
USE blood_donation_db;

CREATE TABLE IF NOT EXISTS admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS donor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS patient (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS blood_request (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    quantity INT NOT NULL,
    hospital_name VARCHAR(150) NOT NULL,
    hospital_location VARCHAR(150) NOT NULL,
    doctor_description TEXT NOT NULL,
    emergency_level VARCHAR(30) NOT NULL,
    required_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_blood_request_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE TABLE IF NOT EXISTS donor_notification (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    request_id BIGINT NOT NULL,
    donor_id BIGINT NOT NULL,
    notification_status VARCHAR(20) NOT NULL DEFAULT 'UNREAD',
    CONSTRAINT fk_notification_request FOREIGN KEY (request_id) REFERENCES blood_request(id) ON DELETE CASCADE,
    CONSTRAINT fk_notification_donor FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE,
    UNIQUE KEY uq_notification (request_id, donor_id)
);

CREATE TABLE IF NOT EXISTS donor_response (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    request_id BIGINT NOT NULL,
    donor_id BIGINT NOT NULL,
    response VARCHAR(20) NOT NULL,
    response_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_response_request FOREIGN KEY (request_id) REFERENCES blood_request(id) ON DELETE CASCADE,
    CONSTRAINT fk_response_donor FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE,
    UNIQUE KEY uq_response (request_id, donor_id)
);
