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


END;