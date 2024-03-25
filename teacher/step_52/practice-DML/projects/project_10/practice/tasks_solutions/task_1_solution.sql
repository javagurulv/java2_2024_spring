
SELECT city, MAX(amount)
FROM transactions
GROUP BY city;
