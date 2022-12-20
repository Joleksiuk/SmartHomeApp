CREATE TABLE IF NOT EXISTS public.commands
(
    id bigint NOT NULL,
    name text NOT NULL,
    category text NOT NULL,
    params text NOT NULL,
    requestMethod text NOT NULL,
    PRIMARY KEY (id)
);