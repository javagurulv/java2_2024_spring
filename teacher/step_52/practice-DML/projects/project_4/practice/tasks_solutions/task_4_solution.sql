
-- Variant 1 (using IN (...))
SELECT * FROM movies
WHERE director IN ("John Lasseter", "Pete Docter", "Brad Bird");


-- Variant 2 (using OR)
SELECT * FROM movies
WHERE director = "John Lasseter"
     OR director = "Pete Docter"
     OR director = "Brad Bird";

