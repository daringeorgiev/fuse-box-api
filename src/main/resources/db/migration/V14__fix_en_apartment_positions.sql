-- Fix: row-2 fuse positions were inserted at 13–22/23 but PanelGrid renders row 2 starting at pos 12.
-- Shift all row-2 fuses down by 1 (13→12 … 22→21) for both English apartment panels.

UPDATE fuse f
SET position = f.position - 1
FROM panel p
WHERE f.panel_id = p.id
  AND p.name = '2-Bedroom Apartment'
  AND f.position >= 13;

UPDATE fuse f
SET position = f.position - 1
FROM panel p
WHERE f.panel_id = p.id
  AND p.name = '3-Bedroom Apartment'
  AND f.position >= 13;
