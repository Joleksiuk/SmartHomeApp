BEGIN;
CREATE TABLE IF NOT EXISTS public.body
(
    id bigint NOT NULL,
    component_id bigint REFERENCES components(id) ,
    code text NOT NULL,
    value_type text NOT NULL,
    ok_values text,
    PRIMARY KEY (id)
);

INSERT INTO body (id, component_id, code, value_type, ok_values) VALUES (1, 1,'switch_led','boolean','{}');
INSERT INTO body (id, component_id, code, value_type, ok_values) VALUES (2, 1,'work_mode','Enum','{\"range\":[\"white\",\"colour\",\"scene\",\"music\"]}');
INSERT INTO body (id, component_id, code, value_type, ok_values) VALUES (3, 1,'colour_data','Json','{\"h\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":360,\"step\":1},\"s\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1},\"v\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1}}');
INSERT INTO body (id, component_id, code, value_type, ok_values) VALUES (4, 1,'scene_data','Json','{\"scene_num\":{\"min\":1,\"scale\":0,\"max\":8,\"step\":1},\"scene_units\": {\"unit_change_mode\":{\"range\":[\"static\",\"jump\",\"gradient\"]},\"unit_switch_duration\":{\"min\":0,\"scale\":0,\"max\":100,\"step\":1},\"unit_gradient_duration\":{\"min\":0,\"scale\":0,\"max\":100,\"step\":1},\"bright\":{\"min\":0,\"scale\":0,\"max\":1000,\"step\":1},\"temperature\":{\"min\":0,\"scale\":0,\"max\":1000,\"step\":1},\"h\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":360,\"step\":1},\"s\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1},\"v\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1}}}');
INSERT INTO body (id, component_id, code, value_type, ok_values) VALUES (5, 1,'countdown','Integer','{\"min\":0,\"max\":86400,\"scale\":0,\"step\":1}');
INSERT INTO body (id, component_id, code, value_type, ok_values) VALUES (6, 1,'music_data','Json','{\"change_mode\":{\"range\":[\"direct\",\"gradient\"]}, \"bright\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1}, \"temperature\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1}, \"h\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":360,\"step\":1},\"s\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":255,\"step\":1},\"v\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":255,\"step\":1}}');
INSERT INTO body (id, component_id, code, value_type, ok_values) VALUES (7, 1,'control_data','Json','{\"change_mode\":{\"range\":[\"direct\",\"gradient\"]}, \"bright\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1}, \"temperature\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":1000,\"step\":1}, \"h\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":360,\"step\":1},\"s\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":255,\"step\":1},\"v\":{\"min\":0,\"scale\":0,\"unit\":\"\",\"max\":255,\"step\":1}}');

END;