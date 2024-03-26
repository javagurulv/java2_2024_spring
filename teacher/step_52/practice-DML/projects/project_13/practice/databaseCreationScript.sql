
CREATE SCHEMA IF NOT EXISTS sql_practice_dml_lesson_13
DEFAULT CHARACTER SET utf8 ;

USE sql_practice_dml_lesson_13;

commit;


CREATE TABLE movies (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    year_of_issue INT NOT NULL,
    length_minutes INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE boxoffices (
    movie_id BIGINT NOT NULL,
    rating DOUBLE NOT NULL,
    domestic_sales INT NOT NULL,
    international_sales INT NOT NULL
);

commit;


INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(1, "Toy Story", "John Lasseter", 1995, 81);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(2, "A Bug's Life", "John Lasseter", 1995, 95);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(3, "Toy Story 2", "John Lasseter", 1999, 93);

commit;

INSERT INTO boxoffices(movie_id, rating, domestic_sales, international_sales)
VALUES(1, 8.2, 380843261, 555900000);

INSERT INTO boxoffices(movie_id, rating, domestic_sales, international_sales)
VALUES(2, 7.4, 268492764, 475066843);

INSERT INTO boxoffices(movie_id, rating, domestic_sales, international_sales)
VALUES(3, 8, 206445654, 417277164);

commit;