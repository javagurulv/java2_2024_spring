CREATE USER 'InsuranceApplication'@'localhost' IDENTIFIED BY 'javaguru';
GRANT ALL PRIVILEGES ON *.* TO 'InsuranceApplication'@'localhost';
FLUSH PRIVILEGES;
-- Will reduce privileges later to particular database and particular operations when I will be sure,
-- that all integrations are set up correct and are working
