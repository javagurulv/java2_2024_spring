SELECT * FROM movies
WHERE (director = "Brad Bird" OR director = "Andrew Stanton")
        AND length_minutes > 110;