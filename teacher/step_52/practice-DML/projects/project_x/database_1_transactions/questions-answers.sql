1. Найдите имена всех трейдеров.
SELECT fullName FROM traders;

2. Отсортируйте имена трейдеров в алфавитном порядке.
SELECT traders.fullName
FROM traders
ORDER BY traders.fullName ASC;

3. Выведите все города в которых работают трейдеры
в обратном алфавитному порядке (без повторений).
SELECT traders.city
FROM traders
GROUP BY traders.city
ORDER BY traders.city DESC;

4. Найти все транзакции трейдера "Jane Smith".
SELECT transactions.*
FROM transactions
LEFT JOIN traders
ON transactions.trader_id = traders.id
WHERE traders.fullName = "Jane Smith";


5. Каков общий объем транзакций для каждого трейдера за определенный год?
SELECT
    t.fullName AS TraderFullName,
    SUM(tr.amount) AS TotalTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    tr.transaction_year = <год>
GROUP BY
    t.fullName;


6. Какие трейдеры совершили наибольшую сумму транзакций за последний год?
SELECT
    t.fullName AS TraderFullName,
    SUM(tr.amount) AS TotalTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    tr.transaction_year = YEAR(CURRENT_DATE()) - 1 -- Последний год
GROUP BY
    t.fullName
ORDER BY
    TotalTransactionAmount DESC
LIMIT 5; -- Получить топ 5 трейдеров


7. В каком городе находится трейдер с наибольшим общим объемом транзакций?
SELECT
    t.city AS TraderCity,
    SUM(tr.amount) AS TotalTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
GROUP BY
    t.city
ORDER BY
    TotalTransactionAmount DESC
LIMIT 1; -- Получить только первую запись с наибольшим объемом транзакций


8. Какова средняя сумма транзакций для каждого трейдера?
SELECT
    t.fullName AS TraderFullName,
    AVG(tr.amount) AS AverageTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
GROUP BY
    t.fullName;


9. Какова сумма транзакций для каждого года?
SELECT
    transaction_year,
    SUM(amount) AS TotalTransactionAmount
FROM
    transactions
GROUP BY
    transaction_year;


10. Какие трейдеры не совершили ни одной транзакции?
SELECT
    t.fullName AS TraderFullName
FROM
    traders t
LEFT JOIN
    transactions tr ON t.id = tr.trader_id
WHERE
    tr.id IS NULL;


11. Какой год имел наибольший объем транзакций?
SELECT
    transaction_year,
    SUM(amount) AS TotalTransactionAmount
FROM
    transactions
GROUP BY
    transaction_year
ORDER BY
    TotalTransactionAmount DESC
LIMIT 1;


12. Каково общее количество транзакций для каждого города?
SELECT
    t.city AS TraderCity,
    COUNT(*) AS TotalTransactionCount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
GROUP BY
    t.city;


13. Какова сумма транзакций для каждого трейдера в заданном году?
SELECT
    t.fullName AS TraderFullName,
    SUM(tr.amount) AS TotalTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    tr.transaction_year = <год>
GROUP BY
    t.fullName;


14. Какие трейдеры совершили транзакции наибольшей суммой за определенный период времени?
SELECT
    t.fullName AS TraderFullName,
    SUM(tr.amount) AS TotalTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    tr.transaction_year >= <начальный_год>
    AND tr.transaction_year <= <конечный_год>
GROUP BY
    t.fullName
ORDER BY
    TotalTransactionAmount DESC;


15. Какие трейдеры имеют наибольшее количество транзакций в городе "New York"?
SELECT
    t.fullName AS TraderFullName,
    COUNT(*) AS TransactionCount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    t.city = 'New York'
GROUP BY
    t.fullName
ORDER BY
    TransactionCount DESC;


16. Какая была самая большая сумма транзакции за каждый год?
SELECT
    transaction_year,
    MAX(amount) AS MaxTransactionAmount
FROM
    transactions
GROUP BY
    transaction_year;


17. Какой трейдер имеет наибольший средний объем транзакций за последние 3 года?
SELECT
    t.fullName AS TraderFullName,
    AVG(tr.amount) AS AverageTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    tr.transaction_year >= YEAR(CURRENT_DATE()) - 2 -- Последние 3 года
GROUP BY
    t.fullName
ORDER BY
    AverageTransactionAmount DESC
LIMIT 1;


18. В каких годах были совершены транзакции с наименьшей суммой?
SELECT
    transaction_year,
    MIN(amount) AS MinTransactionAmount
FROM
    transactions
GROUP BY
    transaction_year;


19. Каково общее количество трейдеров в каждом городе?
SELECT
    city,
    COUNT(*) AS TraderCount
FROM
    traders
GROUP BY
    city;


20. Какие трейдеры совершили самую высокую транзакцию за год 2022?
SELECT
    t.fullName AS TraderFullName,
    tr.amount AS TransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    tr.transaction_year = 2022
    AND tr.amount = (
        SELECT MAX(amount)
        FROM transactions
        WHERE transaction_year = 2022
    );


21. Какова общая сумма транзакций для каждого трейдера в городе "London" за последние 5 лет?
SELECT
    t.fullName AS TraderFullName,
    SUM(tr.amount) AS TotalTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
WHERE
    t.city = 'London'
    AND tr.transaction_year >= YEAR(CURRENT_DATE()) - 4 -- Последние 5 лет
GROUP BY
    t.fullName;


22. Какой город имеет наибольшую среднюю сумму транзакций?
SELECT
    t.city AS TraderCity,
    AVG(tr.amount) AS AverageTransactionAmount
FROM
    traders t
    INNER JOIN transactions tr ON t.id = tr.trader_id
GROUP BY
    t.city
ORDER BY
    AverageTransactionAmount DESC
LIMIT 1;
