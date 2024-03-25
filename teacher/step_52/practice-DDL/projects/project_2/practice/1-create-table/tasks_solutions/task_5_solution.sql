
CREATE TABLE doctors (
    id BIGINT AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    cabinet_number INT NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    personal_code VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE doctor_appointments (
    id BIGINT AUTO_INCREMENT,
    doctor_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    visit_date DATE NOT NULL,
    visit_time_from TIME NOT NULL,
    visit_time_to TIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);
