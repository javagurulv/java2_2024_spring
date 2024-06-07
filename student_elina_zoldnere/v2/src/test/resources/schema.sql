
CREATE TABLE classifiers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_classifiers_title ON classifiers(title);

CREATE TABLE classifier_values (
  id BIGINT NOT NULL AUTO_INCREMENT,
  classifier_id BIGINT NOT NULL,
  ic VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (classifier_id) REFERENCES classifiers(id)
);

CREATE UNIQUE INDEX ix_classifier_values_ic
ON classifier_values(ic);

CREATE TABLE country_default_day_rate (
  id BIGINT NOT NULL AUTO_INCREMENT,
  country_ic VARCHAR(100) NOT NULL,
  default_day_rate DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_country_default_day_rate_country_ic
ON country_default_day_rate (country_ic);

CREATE TABLE travel_medical_age_coefficient (
    id BIGINT NOT NULL AUTO_INCREMENT,
    age_from INT NOT NULL,
    age_to INT NOT NULL,
    coefficient DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medical_risk_limit_level (
   id BIGINT NOT NULL AUTO_INCREMENT,
   medical_risk_limit_level_ic VARCHAR(100) NOT NULL,
   coefficient DECIMAL(10,2) NOT NULL,
   PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_medical_risk_limit_level_ic
ON medical_risk_limit_level (medical_risk_limit_level_ic);

CREATE TABLE persons (
   id BIGINT NOT NULL AUTO_INCREMENT,
   first_name VARCHAR(200) NOT NULL,
   last_name VARCHAR(200) NOT NULL,
   personal_code VARCHAR(100) NOT NULL,
   birth_date TIMESTAMP NOT NULL,
   PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_unique_persons ON persons(first_name, last_name, personal_code);

CREATE TABLE agreements (
   id BIGINT NOT NULL AUTO_INCREMENT,
   date_from TIMESTAMP NOT NULL,
   date_to TIMESTAMP NOT NULL,
   country VARCHAR(100) NOT NULL,
   premium DECIMAL(10,2) NOT NULL,
   uuid VARCHAR(36) NOT NULL,
   PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_agreements_uuid ON agreements(uuid);

CREATE TABLE selected_risks (
   id BIGINT NOT NULL AUTO_INCREMENT,
   agreement_id BIGINT NOT NULL,
   risk_ic VARCHAR(100) NOT NULL,
   PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_selected_risks_agreement_id_risk_ic
ON selected_risks(agreement_id, risk_ic);

CREATE TABLE agreement_persons (
    id BIGINT NOT NULL AUTO_INCREMENT,
    agreement_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    medical_risk_limit_level VARCHAR(100),
    travel_cost DECIMAL(10,2),
    PRIMARY KEY (id),
    FOREIGN KEY (agreement_id) REFERENCES agreements(id),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);

CREATE TABLE agreement_person_risks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    agreement_person_id BIGINT NOT NULL,
    risk_ic VARCHAR(100) NOT NULL,
    premium DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (agreement_person_id) REFERENCES agreement_persons(id)
);

CREATE TABLE travel_cost_coefficient (
    id BIGINT NOT NULL AUTO_INCREMENT,
    travel_cost_from DECIMAL(10,2) NOT NULL,
    travel_cost_to DECIMAL(10,2) NOT NULL,
    coefficient DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE travel_cancellation_age_coefficient (
    id BIGINT NOT NULL AUTO_INCREMENT,
    age_from INT NOT NULL,
    age_to INT NOT NULL,
    coefficient DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE travel_cancellation_country_safety_rating_coefficient (
  id BIGINT NOT NULL AUTO_INCREMENT,
  country_ic VARCHAR(100) NOT NULL,
  coefficient DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_travel_cancellation_country_safety_rating_coefficient_country_ic
ON travel_cancellation_country_safety_rating_coefficient (country_ic);

CREATE TABLE exported_agreements (
    id BIGINT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(255) NOT NULL,
    exported_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_exported_agreements_uuid ON exported_agreements (uuid);