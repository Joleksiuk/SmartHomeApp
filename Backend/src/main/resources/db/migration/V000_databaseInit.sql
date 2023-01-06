BEGIN;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    username text NOT NULL,
    email text,
    password text,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO roles(id,name) VALUES (1,'ROLE_ADMIN');
INSERT INTO roles(id,name) VALUES (2,'ROLE_MODERATOR');
INSERT INTO roles(id,name) VALUES (3,'ROLE_USER');


CREATE TABLE IF NOT EXISTS public.components
(
    id bigint NOT NULL,
    name text NOT NULL,
    image_path text,
    brand text NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO components (id,name,image_path,brand) VALUES (1,'LED', 'https://www.houseiq.pl/userdata/public/gfx/11868.jpg','TUYA');
INSERT INTO components (id,name,image_path,brand) VALUES (2,'Shelly Duo', 'https://shelly-api-docs.shelly.cloud/gen1/images/shelly/shellyduo-product.jpg','Shelly');
INSERT INTO components (id,name,image_path,brand) VALUES (3,'Tuya Smart Plug', 'https://www.expert4house.com/1042-large_default/gniazdo-tuya-smart-wifi.jpg','TUYA');


END;