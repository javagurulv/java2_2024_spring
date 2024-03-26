
-- Variant 1 (using NOT IN (...))
SELECT * FROM movies
WHERE director NOT IN ("John Lasseter", "Pete Docter", "Brad Bird");


-- Variant 2 (using AND)
SELECT * FROM movies
WHERE director != "John Lasseter"
     AND director != "Pete Docter"
     AND director != "Brad Bird";

