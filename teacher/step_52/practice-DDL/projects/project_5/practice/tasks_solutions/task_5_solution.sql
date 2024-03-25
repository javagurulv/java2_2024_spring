
CREATE ROLE local_read_only_role;

GRANT SELECT ON sql_practice_ddl_lesson_5.* TO local_read_only_role;

CREATE USER 'app_read_only_user'@'localhost' IDENTIFIED BY 'password';

GRANT local_read_only_role TO 'app_read_only_user'@'localhost';
