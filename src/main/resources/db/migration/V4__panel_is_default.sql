ALTER TABLE panel
    ADD COLUMN is_default BOOLEAN NOT NULL DEFAULT false;

UPDATE panel SET is_default = true WHERE name = 'Main Distribution Board';
