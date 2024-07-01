INSERT INTO classifiers(title, description) VALUES
("RISK_TYPE", "risk type");

SET @classifier_id = (SELECT `id` FROM `classifiers` WHERE `title` = 'RISK_TYPE');

INSERT INTO classifier_values(classifier_id, ic, description) VALUES
(@classifier_id, "TRAVEL_MEDICAL", "medical risk"),
(@classifier_id, "TRAVEL_CANCELLATION", "travel cancellation risk"),
(@classifier_id, "TRAVEL_LOSS_BAGGAGE", "risk of lost baggage"),
(@classifier_id, "TRAVEL_THIRD_PARTY_LIABILITY", "third party liability risk"),
(@classifier_id, "TRAVEL_EVACUATION", "evacuation risk"),
(@classifier_id, "TRAVEL_SPORT_ACTIVITIES", "risk of sporting events");

SELECT * from classifiers;
SELECT * from classifier_values;