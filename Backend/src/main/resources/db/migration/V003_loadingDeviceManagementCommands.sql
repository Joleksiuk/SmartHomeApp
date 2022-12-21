BEGIN;

INSERT INTO commands (id, name, category, params, request_method) VALUES (8, 'Query Zigbee devices under the gateway', 'Device Management', 'device_id','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (9, 'Batch query equipment factory information', 'Device Management', 'device_ids','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (10, 'Get Device List','Device Management','page_size,page_no,device_ids,product_id,schema,last_id,start_time,end_time','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (11, 'Modify device name','Device Management', 'device_id,Body','PUT');
INSERT INTO commands (id, name, category, params, request_method) VALUES (12, 'Query device log','Device Management', 'device_id,type,start_time,end_time,codes,start_row_key,query_type,last_row_key,last_event_time,size','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (13, 'Restore device factory settings','Device Management', 'device_id','PUT');
INSERT INTO commands (id, name, category, params, request_method) VALUES (14, 'Get device details', 'Device Management', 'device_id','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (15, 'Remove device', 'Device Management', 'device_id','DELETE');
INSERT INTO commands (id, name, category, params, request_method) VALUES (16, 'Get User Device List','Device Management','uid,from,page_no,page_size','GET');
INSERT INTO commands (id, name, category, params, request_method) VALUES (17, 'Delete User','Device Management', 'device_id,user_id','DELETE');

END;