
CREATE SCHEMA IF NOT EXISTS sql_practice_dml_lesson_4
DEFAULT CHARACTER SET utf8 ;

USE sql_practice_dml_lesson_4;

commit;


CREATE TABLE movies (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    year_of_issue INT NOT NULL,
    length_minutes INT NOT NULL,
    PRIMARY KEY(id)
);

commit;


INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(1, "Toy Story", "John Lasseter", 1995, 81);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(2, "A Bug's Life", "John Lasseter", 1998, 95);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(3, "Toy Story 2", "John Lasseter", 1999, 93);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(4, "Monsters, Inc.", "Pete Docter", 2001, 92);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(5, "Finding Nemo", "Andrew Stanton", 2003, 107);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(6, "The Incredibles", "Brad Bird", 2004, 116);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(7, "Cars", "John Lasseter", 2006, 117);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(8, "Ratatouille", "Brad Bird", 2007, 115);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(9, "WALL-E", "Andrew Stanton", 2008, 104);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(10, "Up", "Pete Docter", 2009, 101);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(11, "Toy Story 3", "Lee Unkrich", 2010, 103);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(12, "Cars 2", "John Lasseter", 2011, 120);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(13, "Brave", "Brenda Chapman", 2012, 102);

INSERT INTO movies(id, title, director, year_of_issue, length_minutes)
VALUES(14, "Monsters University", "Dan Scanlon", 2013, 110);

commit;
