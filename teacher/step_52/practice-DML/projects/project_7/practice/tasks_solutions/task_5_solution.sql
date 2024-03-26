-- Variant 1
SELECT DISTINCT year_of_issue
FROM movies
ORDER BY year_of_issue DESC
LIMIT 1;

-- Variant 2
SELECT DISTINCT(year_of_issue)
FROM movies
ORDER BY year_of_issue DESC
LIMIT 1;
