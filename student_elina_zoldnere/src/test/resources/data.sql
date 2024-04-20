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