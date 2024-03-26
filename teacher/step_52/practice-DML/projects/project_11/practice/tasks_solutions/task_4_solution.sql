
SELECT movies.*,
       boxoffices.domestic_sales,
       boxoffices.international_sales
FROM movies
INNER JOIN boxoffices
ON movies.id = boxoffices.movie_id
WHERE boxoffices.international_sales < boxoffices.domestic_sales
ORDER BY boxoffices.domestic_sales DESC;
