ALTER TABLE panel ADD COLUMN user_id VARCHAR(128);

CREATE INDEX idx_panel_user_id ON panel (user_id);
