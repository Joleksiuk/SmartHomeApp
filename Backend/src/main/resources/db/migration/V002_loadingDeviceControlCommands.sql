BEGIN;

INSERT INTO commands (id, name, category, params, request_method) VALUES (1, 'Modify function point name', 'Device Control', 'device_id,function_code,Body','PUT');
INSERT INTO commands (id, name, category, params, request_method) VALUES (2, 'Get the instruction set supported by the device', 'Device Control', 'device_id','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (3, 'Get device specification attributes','Device Control','device_id','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (4, 'Get the instructions set by category','Device Control', 'category','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (5, 'Get the instruction sets in bulk','Device Control', 'device_ids','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (6, 'Get Device Specification Attribute','Device Control', 'device_id','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (7, 'Control Device','Device Control', 'device_id,Body','POST');


END;