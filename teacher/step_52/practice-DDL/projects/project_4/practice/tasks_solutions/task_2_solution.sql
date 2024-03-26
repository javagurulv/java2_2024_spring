
CREATE VIEW unique_asc_directors AS
SELECT DISTINCT director
FROM movies
ORDER BY director ASC;

commit;