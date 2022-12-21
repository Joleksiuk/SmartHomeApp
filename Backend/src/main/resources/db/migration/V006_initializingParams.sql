BEGIN;

INSERT INTO parameters (id, name, type, description) VALUES (1,'device_id','String','The device ID.');
INSERT INTO parameters (id, name, type, description) VALUES (2,'device_ids','List<String>','The list of device IDs, separated with commas (,).');
INSERT INTO parameters (id, name, type, description) VALUES (3,'category','String','');
INSERT INTO parameters (id, name, type, description) VALUES (4,'product_id','String','The unique ID of a specified application.');
INSERT INTO parameters (id, name, type, description) VALUES (5,'schema','String','The unique ID of a specified application.');
INSERT INTO parameters (id, name, type, description) VALUES (6,'last_id','String','The last query ID of a specified device.');
INSERT INTO parameters (id, name, type, description) VALUES (7,'page_size','Integer','The number of entries returned on each page.');
INSERT INTO parameters (id, name, type, description) VALUES (8,'page_no','Integer','The current page number.');
INSERT INTO parameters (id, name, type, description) VALUES (9,'start_time','Long','The 10-digit timestamp of the start time for the query.');
INSERT INTO parameters (id, name, type, description) VALUES (10,'end_time','Long','The 10-digit timestamp of the end time for the query.');

END;