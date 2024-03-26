
SELECT broker, MIN(amount)
FROM transactions
GROUP BY broker;
