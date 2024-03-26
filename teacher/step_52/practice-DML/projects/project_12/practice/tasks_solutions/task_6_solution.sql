
SELECT buildings.*
FROM buildings
LEFT JOIN employees
ON buildings.id = employees.building_id
WHERE employees.id IS NULL
GROUP BY buildings.id;
