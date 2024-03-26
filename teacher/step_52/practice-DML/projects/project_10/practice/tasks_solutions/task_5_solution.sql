
SELECT city, SUM(amount)
FROM transactions
GROUP BY city;
