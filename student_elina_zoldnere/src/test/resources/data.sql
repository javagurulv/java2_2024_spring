INSERT INTO classifiers (title, description)
VALUES ('RISK_TYPE', 't.p. risk type');

INSERT INTO classifier_values (classifier_id, ic, description)
VALUES ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_MEDICAL', 't.p. medical risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_CANCELLATION', 't.p. trip cancellation risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_LOSS_BAGGAGE', 't.p. baggage loss risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_THIRD_PARTY_LIABILITY', 't.p. third party liability risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_EVACUATION', 't.p. evacuation or repatriation risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_SPORT_ACTIVITIES', 't.p. sport activities');

INSERT INTO  classifiers (title, description)
VALUES ('COUNTRY', 'country');

INSERT INTO classifier_values (classifier_id, ic, description)
VALUES ((SELECT id FROM classifiers WHERE title = 'COUNTRY'), 'LATVIA', 'country Latvia'),
       ((SELECT id FROM classifiers WHERE title = 'COUNTRY'), 'SPAIN', 'country Spain'),
       ((SELECT id FROM classifiers WHERE title = 'COUNTRY'), 'JAPAN', 'country Japan');

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

INSERT INTO classifiers (title, description)
VALUES ('MEDICAL_RISK_LIMIT_LEVEL', 'medical risk limit level classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
VALUES ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'),
       'LEVEL_10000', 't.p. medical risk limit level 10 000 EUR'),

       ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'),
       'LEVEL_15000', 't.p. medical risk limit level 15 000 EUR'),

       ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'),
       'LEVEL_20000', 't.p. medical risk limit level 20 000 EUR'),

       ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'),
       'LEVEL_50000', 't.p. medical risk limit level 50 000 EUR');

INSERT INTO medical_risk_limit_level (medical_risk_limit_level_ic, coefficient)
VALUES ('LEVEL_10000', 1.0),
       ('LEVEL_15000', 1.2),
       ('LEVEL_20000', 1.5),
       ('LEVEL_50000', 2.0);
