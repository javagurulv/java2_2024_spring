
CREATE SCHEMA IF NOT EXISTS sql_practice_dml_lesson_12
DEFAULT CHARACTER SET utf8 ;

USE sql_practice_dml_lesson_12;

commit;


CREATE TABLE buildings (
    id BIGINT AUTO_INCREMENT,
    building_name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT,
    building_id BIGINT NOT NULL,
    role VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    years_employed INT NOT NULL,
    PRIMARY KEY(id)
);

commit;


INSERT INTO buildings(id, building_name, capacity)
VALUES(1, "1E", 24);

INSERT INTO buildings(id, building_name, capacity)
VALUES(2, "1W", 32);

INSERT INTO buildings(id, building_name, capacity)
VALUES(3, "2E", 16);

INSERT INTO buildings(id, building_name, capacity)
VALUES(4, "2W", 20);

commit;

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(1, 1, "Engineer", "Becky A.", 4);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(2, 1, "Engineer", "Dan B.", 2);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(3, 1, "Engineer", "Sharon F.", 6);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(4, 1, "Engineer", "Dan M.", 4);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(5, 1, "Engineer", "Malcom S.", 1);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(6, 4, "Artist", "Tylar S.", 2);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(7, 4, "Artist", "Sherman D.", 8);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(8, 4, "Artist", "Jakob J.", 6);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(9, 4, "Artist", "Lillia A.", 7);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(10, 4, "Artist", "Brandon J.", 7);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(11, 1, "Manager", "Brandon J.", 9);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(12, 1, "Manager", "Shirlee M.", 3);

INSERT INTO employees(id, building_id, role, name, years_employed)
VALUES(13, 4, "Manager", "Daria O.", 6);

commit;