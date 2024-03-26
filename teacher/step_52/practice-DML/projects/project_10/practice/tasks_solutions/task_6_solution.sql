
SELECT broker, SUM(amount)
FROM transactions
GROUP BY broker;
