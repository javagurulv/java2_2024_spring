
CREATE SCHEMA IF NOT EXISTS sql_practice_dml_lesson_8
DEFAULT CHARACTER SET utf8 ;

USE sql_practice_dml_lesson_8;

commit;


CREATE TABLE cities (
    id BIGINT AUTO_INCREMENT,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    year_of_foundation INT NOT NULL,
    population INT NOT NULL,
    PRIMARY KEY(id)
);

commit;


INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(1, "Guadalajara", "Mexico", 1542, 1500800);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(2, "Toronto", "Canada", 1793, 2795060);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(3, "Houston", "United States", 1836, 2195914);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(4, "New York", "United States", 1664, 8405837);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(5, "Philadelphia", "United States", 1682, 1553165);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(6, "Havana", "Cuba", 1519, 2106146);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(7, "Mexico City", "Mexico", 1325, 8555500);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(8, "Phoenix", "United States", 1867, 1513367);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(9, "Los Angeles", "United States", 1781, 3884307);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(10, "Montreal", "Canada", 1642, 1717767);

INSERT INTO cities(id, city, country, year_of_foundation, population)
VALUES(11, "Chicago", "United States", 1833, 2718782);

commit;
