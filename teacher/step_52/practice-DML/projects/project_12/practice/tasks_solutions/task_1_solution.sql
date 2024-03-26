
SELECT *
FROM employees
LEFT JOIN buildings
ON employees.building_id = buildings.id
WHERE buildings.building_name = "1E";
