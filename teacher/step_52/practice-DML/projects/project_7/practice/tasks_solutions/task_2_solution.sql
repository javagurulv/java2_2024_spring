-- Variant 1
SELECT DISTINCT director
FROM movies
ORDER BY director ASC;

-- Variant 2
SELECT DISTINCT(director)
FROM movies
ORDER BY director;
