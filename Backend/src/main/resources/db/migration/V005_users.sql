BEGIN;
CREATE TABLE IF NOT EXISTS public.shelly_users
(
    id bigint REFERENCES users(id) ,
    server text NOT NULL,
    auth_key text NOT NULL,
    PRIMARY KEY (id)
    );


CREATE TABLE IF NOT EXISTS public.tuya_users
(
    id bigint REFERENCES users(id) ,
    server text NOT NULL,
    access_id text NOT NULL,
    secret_key text NOT NULL,
    PRIMARY KEY (id)
    );

END;