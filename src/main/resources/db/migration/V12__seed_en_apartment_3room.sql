-- Seed: 3-bedroom apartment, new construction (230 V / 50 Hz, IEC 60898 / 61008)
-- Main breaker: 50A · 2 rows × 12 positions · 2 RCD groups + 22 circuits + 2 spare
--
-- Row 1 (1–12):  lighting dry zones, outlets, A/C units; pos. 12 — spare
-- Row 2 (12–23): RCD 1 → bathroom (light+outlets+boiler); RCD 2 → kitchen+laundry; fridge; pos. 23 — spare
WITH new_panel AS (
    INSERT INTO panel (id, name, location, description, num_rows, fuses_per_row, main_amp, voltage, frequency, is_default, user_id)
    VALUES (
        gen_random_uuid(),
        '3-Bedroom Apartment',
        'Hallway',
        '3-bedroom apartment, new construction — IEC 60898 / 61008, 230 V / 50 Hz',
        2,
        12,
        50,
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
    -- Row 1: dry zones (no RCD) · pos. 12 spare
    ( 1, 'Lighting – living room & hallway',  10, NULL),
    ( 2, 'Lighting – kitchen & dining room',  10, NULL),
    ( 3, 'Lighting – bedroom 1',              10, NULL),
    ( 4, 'Lighting – bedroom 2',              10, NULL),
    ( 5, 'Outlets – living room',             16, NULL),
    ( 6, 'Outlets – bedroom 1',               16, NULL),
    ( 7, 'Outlets – bedroom 2',               16, NULL),
    ( 8, 'Outlets – hallway & corridor',      16, NULL),
    ( 9, 'A/C – living room',                 16, NULL),
    (10, 'A/C – bedroom 1',                   16, NULL),
    (11, 'A/C – bedroom 2',                   16, NULL),
    -- pos. 12 spare

    -- Row 2: wet zones with RCD groups · pos. 23 spare
    (12, 'RCD — Bathroom & boiler',           20, 'RCD'),
    (13, 'Lighting – bathroom & WC',          10, NULL),
    (14, 'Outlets – bathroom',                16, NULL),
    (15, 'Water heater',                      20, NULL),
    (16, 'RCD — Kitchen & laundry',           20, 'RCD'),
    (17, 'Outlets – kitchen',                 16, NULL),
    (18, 'Hob / induction cooktop',           32, NULL),
    (19, 'Oven',                              16, NULL),
    (20, 'Washing machine',                   16, NULL),
    (21, 'Dishwasher',                        16, NULL),
    (22, 'Refrigerator',                      16, NULL)
    -- pos. 23 spare

) AS f(position, label, amperage, description);
