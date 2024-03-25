
-- Variant 1 (using IN (...))
SELECT * FROM movies
WHERE year_of_issue IN (1999, 2001, 2008);


-- Variant 2 (using OR)
SELECT * FROM movies
WHERE year_of_issue = 1999
     OR year_of_issue = 2001
     OR year_of_issue = 2008;

