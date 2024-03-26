
SELECT city, MIN(amount)
FROM transactions
GROUP BY city;
