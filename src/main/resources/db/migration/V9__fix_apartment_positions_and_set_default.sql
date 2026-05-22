-- Fix: row-2 fuse positions were inserted at 13–23 but PanelGrid renders row 2 starting at pos 12.
-- Shift all row-2 fuses down by 1 (13→12 … 23→22) for both apartment panels.
-- Also mark both panels as default so anonymous users can view them.

UPDATE fuse f
SET position = f.position - 1
FROM panel p
WHERE f.panel_id = p.id
  AND p.name = 'Апартамент 3-стаен'
  AND f.position >= 13;

UPDATE fuse f
SET position = f.position - 1
FROM panel p
WHERE f.panel_id = p.id
  AND p.name = 'Апартамент 2-стаен'
  AND f.position >= 13;

UPDATE panel
SET is_default = true
WHERE name IN ('Апартамент 3-стаен', 'Апартамент 2-стаен');
