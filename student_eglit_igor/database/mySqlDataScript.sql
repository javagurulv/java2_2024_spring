INSERT INTO classifiers (title, description)
    VALUES ('RISK_TYPE', 'travel policy risk type classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
    VALUES ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_MEDICAL', 'travel policy medical risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_CANCELLATION', 'travel policy cancellation risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_LOSS_BAGGAGE', 'travel policy loss baggage risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_THIRD_PARTY_LIABILITY', 'travel policy third party liability risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_EVACUATION', 'travel policy evacuation risk'),
       ((SELECT id FROM classifiers WHERE title = 'RISK_TYPE'), 'TRAVEL_SPORT_ACTIVITIES', 'travel policy sport activities risk');

 INSERT INTO classifiers (title, description)
    VALUES ('COUNTRY', 'travel policy country classifier');

 INSERT INTO classifier_values (classifier_id, ic, description)
      VALUES((SELECT id FROM classifiers WHERE title = 'COUNTRY'), 'LATVIA', 'Latvia'),
            ((SELECT id FROM classifiers WHERE title = 'COUNTRY'), 'SPAIN', 'Spain'),
             ((SELECT id FROM classifiers WHERE title = 'COUNTRY'), 'JAPAN', 'Japan');

 INSERT INTO country_default_day_rate (country_ic, default_day_rate)
    VALUES ('LATVIA', 1.00),
            ('SPAIN', 2.50),
            ('JAPAN', 3.50);

 INSERT INTO age_coefficient (age_from, age_to, coefficient)
    VALUES (0, 5, 1.10),
            (6, 10, 0.70),
            (11, 17, 1.00),
            (18, 40, 1.10),
            (41, 65, 1.20),
            (66, 150, 1.50);