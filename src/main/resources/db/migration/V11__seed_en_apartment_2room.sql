-- Seed: 2-bedroom apartment, new construction (230 V / 50 Hz, IEC 60898 / 61008)
-- Main breaker: 40A · 2 rows × 12 positions · 2 RCD groups + 18 circuits + 6 spare
--
-- Row 1 (1–12):  lighting dry zones, outlets, A/C units; pos. 9–12 — spare
-- Row 2 (12–23): RCD 1 → bathroom (light+outlets+boiler); RCD 2 → kitchen+laundry; fridge; pos. 22–23 — spare
WITH new_panel AS (
    INSERT INTO panel (id, name, location, description, num_rows, fuses_per_row, main_amp, voltage, frequency, is_default, user_id)
    VALUES (
        gen_random_uuid(),
        '2-Bedroom Apartment',
        'Hallway',
        '2-bedroom apartment, new construction — IEC 60898 / 61008, 230 V / 50 Hz',
        2,
        12,
        40,
        230,
        50,
        true,
        'VQWf5HpKAdS5FMb3kL9igSjeaYz2'
    )
    RETURNING id
)
INSERT INTO fuse (id, panel_id, position, label, amperage, description)
SELECT gen_random_uuid(), np.id, f.position, f.label, f.amperage, f.description
FROM new_panel np,
(VALUES
    -- Row 1: dry zones (no RCD) · pos. 9–12 spare
    ( 1, 'Lighting – living room & hallway', 10, NULL),
    ( 2, 'Lighting – kitchen',               10, NULL),
    ( 3, 'Lighting – bedroom',               10, NULL),
    ( 4, 'Outlets – living room',            16, NULL),
    ( 5, 'Outlets – bedroom',                16, NULL),
    ( 6, 'Outlets – hallway',                16, NULL),
    ( 7, 'A/C – living room',                16, NULL),
    ( 8, 'A/C – bedroom',                    16, NULL),
    -- pos. 9–12 spare

    -- Row 2: wet zones with RCD groups · pos. 22–23 spare
    (12, 'RCD — Bathroom & boiler',          20, 'RCD'),
    (13, 'Lighting – bathroom',              10, NULL),
    (14, 'Outlets – bathroom',               16, NULL),
    (15, 'Water heater',                     20, NULL),
    (16, 'RCD — Kitchen & laundry',          20, 'RCD'),
    (17, 'Outlets – kitchen',                16, NULL),
    (18, 'Hob / induction cooktop',          32, NULL),
    (19, 'Oven',                             16, NULL),
    (20, 'Washing machine',                  16, NULL),
    (21, 'Refrigerator',                     16, NULL)
    -- pos. 22–23 spare

) AS f(position, label, amperage, description);
