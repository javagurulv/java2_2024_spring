
CREATE SCHEMA IF NOT EXISTS sql_practice_dml_lesson_10
DEFAULT CHARACTER SET utf8 ;

USE sql_practice_dml_lesson_10;

commit;


CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT,
    broker VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    PRIMARY KEY(id)
);

commit;


INSERT INTO transactions(id, broker, city, amount)
VALUES(1, "John", "Toronto", 100);

INSERT INTO transactions(id, broker, city, amount)
VALUES(2, "Kiril", "New York", 200);

INSERT INTO transactions(id, broker, city, amount)
VALUES(3, "Gregor", "Toronto", 50);

INSERT INTO transactions(id, broker, city, amount)
VALUES(4, "Vasilij", "Los Angeles", 250);

INSERT INTO transactions(id, broker, city, amount)
VALUES(5, "Karl", "Los Angeles", 199);

INSERT INTO transactions(id, broker, city, amount)
VALUES(6, "Alexander", "Los Angeles", 49);

INSERT INTO transactions(id, broker, city, amount)
VALUES(7, "Alexander", "Toronto", 155);

INSERT INTO transactions(id, broker, city, amount)
VALUES(8, "John", "Toronto", 300);

INSERT INTO transactions(id, broker, city, amount)
VALUES(9, "Karl", "Los Angeles", 500);

commit;
