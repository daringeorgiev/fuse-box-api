-- Add display sort order to panel; default 100 keeps existing panels out of the way.
-- Lower values appear first for anonymous users.
ALTER TABLE panel ADD COLUMN sort_order INT NOT NULL DEFAULT 100;

-- Make the 3-bedroom EN apartment the landing panel for anonymous users.
UPDATE panel SET sort_order = 1 WHERE name = '3-Bedroom Apartment' AND is_default = true;
