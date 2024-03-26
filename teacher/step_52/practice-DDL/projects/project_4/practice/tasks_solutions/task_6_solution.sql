
CREATE VIEW directors_film_count AS
SELECT movies.director, COUNT(*) AS filmCount
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id
GROUP BY movies.director;

commit;