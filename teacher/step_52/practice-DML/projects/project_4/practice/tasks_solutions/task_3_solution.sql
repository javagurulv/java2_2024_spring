
-- Variant 1
SELECT * FROM movies
WHERE year_of_issue NOT BETWEEN 2000 AND 2010;


-- Variant 2
SELECT * FROM movies
WHERE year_of_issue < 2000 OR year_of_issue > 2010;

