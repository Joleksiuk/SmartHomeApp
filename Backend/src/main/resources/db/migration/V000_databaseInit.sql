BEGIN;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    username character varying[] NOT NULL,
    email character varying[],
    password character varying[],
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL,
    name character varying[] NOT NULL,
    PRIMARY KEY (id)
);

END;