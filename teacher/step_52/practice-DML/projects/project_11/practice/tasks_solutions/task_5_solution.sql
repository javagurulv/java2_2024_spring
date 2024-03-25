
SELECT movies.*, boxoffices.rating
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id
ORDER BY boxoffices.rating DESC;
