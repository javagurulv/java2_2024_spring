
-- Вставка данных в таблицу traders
INSERT INTO traders (fullName, city)
VALUES
    ('John Doe', 'New York'),
    ('Jane Smith', 'London'),
    ('Michael Johnson', 'Paris'),
    ('Emily Brown', 'Tokyo'),
    ('David Wilson', 'Berlin'),
    ('Sarah Davis', 'London'),
    ('Daniel Anderson', 'Toronto'),
    ('Olivia Thomas', 'Moscow'),
    ('Matthew Lee', 'Madrid'),
    ('Sophia Martinez', 'Madrid');


-- Создание временной таблицы для генерации данных
CREATE TEMPORARY TABLE temp_transactions (
    id INT AUTO_INCREMENT,
    trader_id BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    transaction_year INT NOT NULL,
    PRIMARY KEY (id)
);

-- Генерация случайных транзакций
INSERT INTO temp_transactions (trader_id, amount, transaction_year)
SELECT
    (SELECT id FROM traders ORDER BY RAND() LIMIT 1) AS trader_id, -- Случайный выбор трейдера
    ROUND(RAND() * 1000, 2) AS amount, -- Генерация случайной суммы транзакции
    FLOOR(RAND() * 10) + 2013 AS transaction_year -- Генерация случайного года транзакции от 2014 до 2023
FROM
    (SELECT id FROM traders LIMIT 100) AS t; -- Ограничение до 100 записей

INSERT INTO temp_transactions (trader_id, amount, transaction_year)
SELECT
    (SELECT id FROM traders ORDER BY RAND() LIMIT 1) AS trader_id, -- Случайный выбор трейдера
    ROUND(RAND() * 1000, 2) AS amount, -- Генерация случайной суммы транзакции
    FLOOR(RAND() * 10) + 2013 AS transaction_year -- Генерация случайного года транзакции от 2013 до 2023
FROM
    (SELECT id FROM traders LIMIT 100) AS t; -- Ограничение до 100 записей

INSERT INTO temp_transactions (trader_id, amount, transaction_year)
SELECT
    (SELECT id FROM traders ORDER BY RAND() LIMIT 1) AS trader_id, -- Случайный выбор трейдера
    ROUND(RAND() * 1000, 2) AS amount, -- Генерация случайной суммы транзакции
    FLOOR(RAND() * 10) + 2013 AS transaction_year -- Генерация случайного года транзакции от 2014 до 2023
FROM
    (SELECT id FROM traders LIMIT 100) AS t; -- Ограничение до 100 записей

INSERT INTO temp_transactions (trader_id, amount, transaction_year)
SELECT
    (SELECT id FROM traders ORDER BY RAND() LIMIT 1) AS trader_id, -- Случайный выбор трейдера
    ROUND(RAND() * 1000, 2) AS amount, -- Генерация случайной суммы транзакции
    FLOOR(RAND() * 10) + 2013 AS transaction_year -- Генерация случайного года транзакции от 2014 до 2023
FROM
    (SELECT id FROM traders LIMIT 100) AS t; -- Ограничение до 100 записей



-- Вставка данных из временной таблицы в основную таблицу transactions
INSERT INTO transactions (trader_id, amount, transaction_year)
SELECT
    trader_id,
    amount,
    transaction_year
FROM
    temp_transactions;

-- Удаление временной таблицы
DROP TABLE temp_transactions;

COMMIT;

