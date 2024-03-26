
CREATE SCHEMA IF NOT EXISTS sql_practice_ddl_lesson_3
DEFAULT CHARACTER SET utf8;

USE sql_practice_ddl_lesson_3;

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