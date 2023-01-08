BEGIN;

CREATE TABLE IF NOT EXISTS public.houses
(
    id bigint NOT NULL,
    owner_id bigint REFERENCES users(id),
    name text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.devices
(
    id bigint NOT NULL,
    specific_id text NOT NULL,
    component_id bigint REFERENCES components(id),
    house_id bigint REFERENCES houses(id),
    name text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.scenes
(
    id bigint NOT NULL,
    house_id bigint REFERENCES houses(id),
    owner_id bigint REFERENCES users(id),
    name text NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.commands
(
    id bigint NOT NULL,
    code text NOT NULL,
    value text NOT NULL,
    device_id bigint REFERENCES devices(id),
    scene_id bigint REFERENCES scenes(id),
    PRIMARY KEY (id)
);


END;