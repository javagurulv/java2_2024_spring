
CREATE SCHEMA IF NOT EXISTS sql_practice_transactions
DEFAULT CHARACTER SET utf8 ;
USE sql_practice_transactions;

commit;

CREATE TABLE traders (
    id BIGINT AUTO_INCREMENT,
    fullName VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT,
    trader_id BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    transaction_year INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (trader_id) REFERENCES traders(id)
);


commit;