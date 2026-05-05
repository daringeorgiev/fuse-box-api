CREATE TABLE panel (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name         VARCHAR(255) NOT NULL,
    location     VARCHAR(255),
    description  TEXT,
    num_rows     INT          NOT NULL,
    fuses_per_row INT         NOT NULL,
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP
);

CREATE TABLE fuse (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    panel_id    UUID         NOT NULL REFERENCES panel (id) ON DELETE CASCADE,
    position    INT          NOT NULL,
    label       VARCHAR(255) NOT NULL,
    amperage    INT          NOT NULL,
    description TEXT,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP
);

CREATE INDEX idx_fuse_panel_id       ON fuse (panel_id);
CREATE INDEX idx_fuse_panel_position ON fuse (panel_id, position);
