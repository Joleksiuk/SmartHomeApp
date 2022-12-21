BEGIN;
CREATE TABLE IF NOT EXISTS public.parameters
(
    id bigint NOT NULL,
    name text NOT NULL,
    type text NOT NULL,
    description text,
    PRIMARY KEY (id)
);
END;