
SELECT movies.*
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id
ORDER BY boxoffices.rating ASC
LIMIT 1;
