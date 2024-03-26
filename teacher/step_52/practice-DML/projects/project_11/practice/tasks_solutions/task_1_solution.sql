
SELECT movies.*, boxoffices.domestic_sales
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id;
