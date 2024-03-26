
-- Variant 1
SELECT * FROM movies
WHERE length_minutes BETWEEN 100 AND 120;


-- Variant 2
SELECT * FROM movies
WHERE length_minutes >= 100 AND length_minutes <= 120;

