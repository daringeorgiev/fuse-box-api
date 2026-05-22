-- Correct English apartment names to match UK/US bedroom-count convention.
-- Bulgarian "2-стаен" (hall + 1 bedroom) = 1-Bedroom; "3-стаен" (hall + 2 bedrooms) = 2-Bedroom.
UPDATE panel SET name = '1-Bedroom Apartment' WHERE name = '2-Bedroom Apartment' AND is_default = true;
UPDATE panel SET name = '2-Bedroom Apartment' WHERE name = '3-Bedroom Apartment' AND is_default = true;
