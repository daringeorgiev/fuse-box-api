-- Seed: Family house, 2 floors + basement/garage (230 V / 50 Hz, IEC 60898 / 61008)
-- Main breaker: 63A · 3 rows × 12 positions · 3 RCD groups + 28 circuits + 7 spare
--
-- Row 1 (pos. 1–11):   lighting, outlets, A/C units, garage, yard
-- Row 2 (pos. 12–23):  RCD 1 → bathroom 1 + boiler; RCD 2 → kitchen + laundry; pos. 23 — spare
-- Row 3 (pos. 24–35):  RCD 3 → bathroom 2 + terrace; heat pump; underfloor heating; alarm; EV; pos. 32–35 — spare
WITH new_panel AS (
    INSERT INTO panel (id, name, location, description, num_rows, fuses_per_row, main_amp, voltage, frequency, is_default, user_id)
    VALUES (
        gen_random_uuid(),
        'Family House',
        'Utility Room',
        'Family house 2 floors + basement/garage — IEC 60898 / 61008, 230 V / 50 Hz',
        3,
        12,
        63,
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
    -- Row 1: dry zones (no RCD) — pos. 1–11
    ( 1, 'Lighting – ground floor',                  10, NULL),
    ( 2, 'Lighting – upper floor',                   10, NULL),
    ( 3, 'Lighting – basement & garage',             10, NULL),
    ( 4, 'Outlets – living room & dining room',      16, NULL),
    ( 5, 'Outlets – study & lounge',                 16, NULL),
    ( 6, 'Outlets – upper floor bedrooms',           16, NULL),
    ( 7, 'A/C – living room',                        16, NULL),
    ( 8, 'A/C – master bedroom',                     16, NULL),
    ( 9, 'A/C – bedroom 2 & 3',                      16, NULL),
    (10, 'Garage & yard – outlets',                  16, NULL),
    (11, 'Exterior lighting & terrace',              16, NULL),

    -- Row 2: wet zones with RCD groups — pos. 12–23 · pos. 23 spare
    (12, 'RCD — Bathroom 1 & boiler',                25, 'RCD'),
    (13, 'Bathroom 1 – lighting',                    10, NULL),
    (14, 'Bathroom 1 – outlets',                     16, NULL),
    (15, 'Boiler / water heater',                    20, NULL),
    (16, 'RCD — Kitchen & laundry',                  25, 'RCD'),
    (17, 'Outlets – kitchen',                        16, NULL),
    (18, 'Hob / induction cooktop',                  32, NULL),
    (19, 'Oven',                                     16, NULL),
    (20, 'Washing machine',                          16, NULL),
    (21, 'Dishwasher',                               16, NULL),
    (22, 'Refrigerator',                             16, NULL),
    -- pos. 23 spare

    -- Row 3: technical installations with RCD group — pos. 24–35 · pos. 32–35 spare
    (24, 'RCD — Bathroom 2 & terrace',               25, 'RCD'),
    (25, 'Bathroom 2 – lighting',                    10, NULL),
    (26, 'Bathroom 2 – outlets',                     16, NULL),
    (27, 'Heat pump / heating boiler',               16, NULL),
    (28, 'Underfloor heating – ground floor',        16, NULL),
    (29, 'Underfloor heating – upper floor',         16, NULL),
    (30, 'Security system & CCTV',                   10, NULL),
    (31, 'EV charging station',                      32, NULL)
    -- pos. 32–35 spare

) AS f(position, label, amperage, description);
