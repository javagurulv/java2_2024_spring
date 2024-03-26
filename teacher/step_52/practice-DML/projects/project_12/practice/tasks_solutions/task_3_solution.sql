
SELECT *
FROM buildings
RIGHT JOIN employees
ON buildings.id = employees.building_id
WHERE buildings.building_name = "1E";
