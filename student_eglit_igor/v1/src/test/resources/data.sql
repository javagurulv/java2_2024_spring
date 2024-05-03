INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier');

INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_MEDICAL',
    'Travel policy medical risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_CANCELLATION',
    'Travel policy trip cancellation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_LOSS_BAGGAGE',
    'Travel policy baggage lose risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_THIRD_PARTY_LIABILITY',
    'Travel policy third party liability risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_EVACUATION',
    'Travel policy evacuation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_SPORT_ACTIVITIES',
    'Travel policy sport activities risk type'
    FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifiers(title, description)
    VALUES('COUNTRY', 'Country classifier');

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
    SELECT
        cl.id,
        'LATVIA',
        'Latvia'
    FROM classifiers as cl
    WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
    SELECT
        cl.id,
        'SPAIN',
        'Spain'
    FROM classifiers as cl
    WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
    SELECT
        cl.id,
        'JAPAN',
        'Japan'
    FROM classifiers as cl
    WHERE cl.title= 'COUNTRY';

 INSERT INTO country_default_day_rate(country_ic, default_day_rate)
 VALUES('LATVIA', 1.00);

 INSERT INTO country_default_day_rate(country_ic, default_day_rate)
 VALUES('SPAIN', 2.50);

 INSERT INTO country_default_day_rate(country_ic, default_day_rate)
 VALUES('JAPAN', 3.50);

 INSERT INTO age_coefficient (age_from, age_to, coefficient)
     VALUES (0, 5, 1.10),
             (6, 10, 0.70),
             (11, 17, 1.00),
             (18, 40, 1.10),
             (41, 65, 1.20),
             (66, 150, 1.50);

 INSERT INTO classifiers(title, description)
    VALUES('MEDICAL_RISK_LIMIT_LEVEL', 'Medical risk limit level classifier');

 INSERT INTO classifier_values(
        classifier_id,
        ic,
        description)
        SELECT
            cl.id,
            'LEVEL_10000',
            'Medical risk limit level 10000 euro'
        FROM classifiers as cl
        WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

 INSERT INTO classifier_values(
          classifier_id,
          ic,
          description)
          SELECT
                cl.id,
                'LEVEL_15000',
                'Medical risk limit level 15000 euro'
          FROM classifiers as cl
          WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

 INSERT INTO classifier_values(
          classifier_id,
          ic,
          description)
          SELECT
                cl.id,
                'LEVEL_20000',
                'Medical risk limit level 20000 euro'
          FROM classifiers as cl
          WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

  INSERT INTO classifier_values(
          classifier_id,
          ic,
          description)
          SELECT
                cl.id,
                'LEVEL_50000',
                'Medical risk limit level 50000 euro'
          FROM classifiers as cl
          WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

    INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
    VALUES('LEVEL_10000', 1.00),
                ('LEVEL_15000', 1.20),
                ('LEVEL_20000', 1.50),
                ('LEVEL_50000', 2.00);
