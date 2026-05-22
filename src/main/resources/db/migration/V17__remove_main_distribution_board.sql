-- Remove the original generic seed panel; superseded by the apartment and family-house examples.
DELETE FROM fuse  WHERE panel_id = (SELECT id FROM panel WHERE name = 'Main Distribution Board');
DELETE FROM panel WHERE name = 'Main Distribution Board';
