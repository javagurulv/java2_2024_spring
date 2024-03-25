
CREATE ROLE local_insert_update_role;

GRANT SELECT ON sql_practice_ddl_lesson_5.* TO local_insert_update_role;
GRANT INSERT ON sql_practice_ddl_lesson_5.* TO local_insert_update_role;
GRANT UPDATE ON sql_practice_ddl_lesson_5.* TO local_insert_update_role;

CREATE USER 'app_insert_update_user'@'localhost' IDENTIFIED BY 'password';

GRANT local_insert_update_role TO 'app_insert_update_user'@'localhost';
