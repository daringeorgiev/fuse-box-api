-- Seed: 3-bedroom EU residential distribution board (230 V / 50 Hz, IEC 60898 / 61008)
WITH new_panel AS (
    INSERT INTO panel (id, name, location, description, num_rows, fuses_per_row, main_amp, voltage, frequency)
    VALUES (
        gen_random_uuid(),
        'Main Distribution Board',
        'Utility Room',
        '3-bedroom residential — IEC 60898 compliant, 230 V / 50 Hz',
        2,
        12,
        63,
        230,
        50
    )
    RETURNING id
)
INSERT INTO fuse (id, panel_id, position, label, amperage)
SELECT gen_random_uuid(), np.id, f.position, f.label, f.amperage
FROM new_panel np,
(VALUES
    ( 1, 'Ground Floor Lights',   10),
    ( 2, 'Upper Floor Lights',    10),
    ( 3, 'Living Room Sockets',   16),
    ( 4, 'Dining Room Sockets',   16),
    ( 5, 'Kitchen Sockets',       16),
    ( 6, 'Kitchen Appliances',    16),
    ( 7, 'Electric Hob',          32),
    ( 8, 'Built-in Oven',         16),
    ( 9, 'Refrigerator',          16),
    (10, 'Dishwasher (RCD)',      16),
    (11, 'Washing Machine',       16),
    (12, 'Tumble Dryer',          16),
    (13, 'Master Bedroom',        16),
    (14, 'Bedroom 2',             16),
    (15, 'Bedroom 3',             16),
    (16, 'Bathroom (RCD)',        16),
    (17, 'Towel Rail / En-suite', 16),
    (18, 'Electric Boiler',       20),
    (19, 'Heat Pump',             25),
    (20, 'Garage',                16),
    (21, 'Outdoor Sockets (RCD)', 16),
    (22, 'Outdoor Lighting',      10),
    (23, 'EV Charger',            32)
) AS f(position, label, amperage);
