
-- Variant 1 (using NOT IN (...))
SELECT * FROM movies
WHERE year_of_issue NOT IN (1999, 2001, 2008);


-- Variant 2 (using AND)
SELECT * FROM movies
WHERE year_of_issue != 1999
     AND year_of_issue != 2001
     AND year_of_issue != 2008;

