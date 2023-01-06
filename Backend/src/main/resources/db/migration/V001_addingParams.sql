BEGIN;
CREATE TABLE IF NOT EXISTS public.parameters
(
    id bigint NOT NULL,
    code text NOT NULL,
    value text NOT NULL,
    value_type text NOT NULL,
    component_id bigint REFERENCES components(id),
    PRIMARY KEY (id)
);

INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (1,'switch_led','true','Bool',1);
INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (2,'colour_data','#fc51eb','String',1);
INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (3,'intensity','1000','String',1);

INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (4,'switch_1','true','Bool',4);

INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (5,'switch','on','String',3);
INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (6,'brightness','100','String',3);
INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (7,'temp','3500','String',3);
INSERT INTO parameters (id, code, value, value_type,component_id) VALUES (8,'white','100','String',3);


END;