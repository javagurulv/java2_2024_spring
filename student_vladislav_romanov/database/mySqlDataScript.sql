INSERT INTO classifiers(title, description)
    VALUES ('RISK_TYPE', 'Travel policy risk');

INSERT INTO classifier_values(classifier_id, ic, description) 
    SELECT
        cl.id,
        'TRAVEL_MEDICAL',
        'Travel policy medical risk type'
    FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(classifier_id, ic, description) 
    SELECT
        cl.id,
        'TRAVEL_CANCELLATION',
        'travel policy cancellation risk'
    FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values(classifier_id, ic, description)
    SELECT
        cl.id,
        'TRAVEL_LOSS_BAGGAGE',
        'travel policy baggage loss risk'
    FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values(classifier_id, ic, description)
    SELECT
        cl.id,
        'TRAVEL_THIRD_PARTY_LIABILITY',
        'travel policy third part liability risk'
    FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values(classifier_id, ic, description)
    SELECT
        cl.id,
        'TRAVEL_EVACUATION',
        'travel policy evacuation risk'
    FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values(classifier_id, ic, description)
    SELECT
        cl.id,
        'TRAVEL_SPORT_ACTIVITIES',
        'travel policy sport activities risk'
    FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifiers(title, description)
VALUES ('COUNTRY', 'Country of destination');

INSERT INTO classifier_values(classifier_id, ic, description)
SELECT
    cl.id,
    'LATVIA',
    'Latvia'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(classifier_id, ic, description)
SELECT
    cl.id,
    'SPAIN',
    'Spain'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(classifier_id, ic, description)
SELECT
    cl.id,
    'JAPAN',
    'Japan'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';