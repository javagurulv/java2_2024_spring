
CREATE VIEW movie_sales AS
SELECT movies.title, movies.director,
       boxoffices.domestic_sales, boxoffices.international_sales
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id;

commit;

SELECT * FROM movie_sales
WHERE director = "John Lasseter";