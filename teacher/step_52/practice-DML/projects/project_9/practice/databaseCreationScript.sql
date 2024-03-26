
CREATE SCHEMA IF NOT EXISTS sql_practice_dml_lesson_9
DEFAULT CHARACTER SET utf8 ;

USE sql_practice_dml_lesson_9;

commit;


CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(100),
    PRIMARY KEY(id)
);

commit;


INSERT INTO clients(id, first_name, last_name, email, phone)
VALUES(1, "Vasja", "Pupkin", "pupkin@inbox.lv", "+371222222");

INSERT INTO clients(id, first_name, last_name, email, phone)
VALUES(2, "Ljudmila", "Kalashnikova", NULL, "+371333333");

INSERT INTO clients(id, first_name, last_name, email, phone)
VALUES(3, "Aleksandr", "Kalinin", "kalinin@gmail.com", NULL);

INSERT INTO clients(id, first_name, last_name, email, phone)
VALUES(4, "Jelena", "Kalesnikova", NULL, NULL);

INSERT INTO clients(id, first_name, last_name, email, phone)
VALUES(5, "Valerij", "Ostapenko", "ost@inbox.lv", "+371444444");

INSERT INTO clients(id, first_name, last_name, email, phone)
VALUES(6, "Alex", "Molchan", "molchan@inbox.lv", NULL);

INSERT INTO clients(id, first_name, last_name, email, phone)
VALUES(7, "Olesja", "Dorofeeva", NULL, "+371555555");

commit;
