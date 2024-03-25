
SELECT buildings.*,
       employees.role,
       COUNT(*) AS employeeCount
FROM employees
LEFT JOIN buildings
ON employees.building_id = buildings.id
GROUP BY buildings.id, employees.role;
