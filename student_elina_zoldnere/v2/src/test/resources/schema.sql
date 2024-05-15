
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
  PRIMARY KEY (id),
  FOREIGN KEY (country_ic) REFERENCES classifier_values(ic)
);

CREATE UNIQUE INDEX ix_country_default_day_rate_country_ic
ON country_default_day_rate (country_ic);

CREATE TABLE age_coefficient (
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
   PRIMARY KEY (id)
);

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
    PRIMARY KEY (id),
    FOREIGN KEY (agreement_id) REFERENCES agreements(id),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);