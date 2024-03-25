
SELECT DISTINCT(email) AS client_unique_emails
FROM clients
WHERE email IS NOT NULL;