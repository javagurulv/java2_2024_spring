
CREATE UNIQUE INDEX index_unique_movies_title_director
ON movies (title, director);

commit;