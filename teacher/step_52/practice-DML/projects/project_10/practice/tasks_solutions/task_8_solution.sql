
SELECT broker, AVG(amount)
FROM transactions
GROUP BY broker;
