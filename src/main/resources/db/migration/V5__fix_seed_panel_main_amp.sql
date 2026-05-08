-- V3 seeded the default EU panel with main_amp = 63 A, which is correct for a
-- small flat. A 3-bedroom house with EV charger, heat pump, and electric hob
-- (23 circuits, ~395 A installed capacity) requires at least a 100 A supply.
-- Also backfill voltage/frequency for any panel that still carries the old
-- column defaults (240 V / 60 Hz) from V2.
UPDATE panel
SET
    main_amp  = 100,
    voltage   = 230,
    frequency = 50
WHERE is_default = true
  AND main_amp   = 63;

UPDATE panel
SET
    voltage   = 230,
    frequency = 50
WHERE voltage = 240
  AND frequency = 60;
