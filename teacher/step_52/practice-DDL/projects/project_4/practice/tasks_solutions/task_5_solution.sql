
CREATE VIEW movie_ratings AS
SELECT movies.title, boxoffices.rating
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id;

commit;