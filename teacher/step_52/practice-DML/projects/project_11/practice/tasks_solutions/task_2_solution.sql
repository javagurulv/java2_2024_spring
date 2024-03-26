
SELECT movies.*, boxoffices.international_sales
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id
ORDER BY movies.title ASC;
