INSERT INTO classifiers (title, description)
VALUES ('RISK_TYPE', 't.p. risk type');

SET @classifier_id = LAST_INSERT_ID();

INSERT INTO classifier_values (classifier_id, ic, description)
VALUES (@classifier_id, 'TRAVEL_MEDICAL', 't.p. medical risk'),
       (@classifier_id, 'TRAVEL_CANCELLATION', 't.p. trip cancellation risk'),
       (@classifier_id, 'TRAVEL_LOSS_BAGGAGE', 't.p. baggage loss risk'),
       (@classifier_id, 'TRAVEL_THIRD_PARTY_LIABILITY', 't.p. third party liability risk'),
       (@classifier_id, 'TRAVEL_EVACUATION', 't.p. evacuation or repatriation risk'),
       (@classifier_id, 'TRAVEL_SPORT_ACTIVITIES', 't.p. sport activities');

INSERT INTO  classifiers (title, description)
VALUES ('COUNTRY', 'country');

SET @classifier_id = LAST_INSERT_ID();

INSERT INTO classifier_values (classifier_id, ic, description)
VALUES (@classifier_id, 'LATVIA', 'country Latvia'),
       (@classifier_id, 'SPAIN', 'country Spain'),
       (@classifier_id, 'JAPAN', 'country Japan');

INSERT INTO country_default_day_rate (country_ic, default_day_rate)
VALUES ('LATVIA', 1.00),
       ('SPAIN', 2.50),
       ('JAPAN', 3.50);

INSERT INTO age_coefficient (age_from, age_to, coefficient)
VALUES (0, 5, 1.1),
       (6, 10, 0.7),
       (11, 17, 1.0),
       (18, 40, 1.1),
       (41, 65, 1.2),
       (66, 150, 1.5);
