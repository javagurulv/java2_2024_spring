
SELECT DISTINCT(phone) AS client_unique_phones
FROM clients
WHERE phone IS NOT NULL;