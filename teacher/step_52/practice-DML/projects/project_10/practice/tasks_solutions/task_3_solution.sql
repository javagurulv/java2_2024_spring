
SELECT broker, MAX(amount)
FROM transactions
GROUP BY broker;
