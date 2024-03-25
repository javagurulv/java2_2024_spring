
SELECT buildings.*,
       SUM(employees.years_employed) AS years_employed
FROM employees
LEFT JOIN buildings
ON employees.building_id = buildings.id
GROUP BY buildings.id;
