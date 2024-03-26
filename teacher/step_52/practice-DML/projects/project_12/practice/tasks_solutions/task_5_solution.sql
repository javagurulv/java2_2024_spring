
SELECT buildings.*
FROM employees
LEFT JOIN buildings
ON employees.building_id = buildings.id
GROUP BY buildings.id;
