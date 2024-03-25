
SELECT buildings.*, employees.role
FROM buildings
LEFT JOIN employees
ON buildings.id = employees.building_id
GROUP BY buildings.id, employees.role;
