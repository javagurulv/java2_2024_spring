
-- Good variant
SELECT *
FROM employees
LEFT JOIN buildings
ON employees.building_id = buildings.id
WHERE buildings.building_name = "2E";


-- Bad variant (compare query result with Good variant)
SELECT *
FROM buildings
LEFT JOIN employees
ON buildings.id = employees.building_id
WHERE buildings.building_name = "2E";
