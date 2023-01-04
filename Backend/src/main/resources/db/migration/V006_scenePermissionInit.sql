BEGIN;

CREATE TABLE IF NOT EXISTS public.houses
(
    id bigint NOT NULL,
    ownerId bigint REFERENCES users(id),
    name text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.devices
(
    id bigint NOT NULL,
    componentId bigint REFERENCES components(id),
    houseId bigint REFERENCES houses(id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.commands
(
    id bigint NOT NULL,
    componentId bigint REFERENCES components(id),
    endpoint text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.scenes
(
    id bigint NOT NULL,
    houseId bigint REFERENCES houses(id),
    ownerId bigint REFERENCES users(id),
    name text NOT NULL,
    PRIMARY KEY (id)
);

END;