
-- Variant 1
SELECT * FROM movies
WHERE year_of_issue BETWEEN 2001 AND 2010;


-- Variant 2
SELECT * FROM movies
WHERE year_of_issue >= 2001 AND year_of_issue <= 2010;

