-- Variant 1
SELECT DISTINCT year_of_issue
FROM movies
ORDER BY year_of_issue DESC;

-- Variant 2
SELECT DISTINCT(year_of_issue)
FROM movies
ORDER BY year_of_issue DESC;
