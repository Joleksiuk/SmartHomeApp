BEGIN;

CREATE TABLE IF NOT EXISTS public.components
(
    id bigint NOT NULL,
    name text NOT NULL,
    imagePath text,
    PRIMARY KEY (id)
);

INSERT INTO components (id,name,imagePath) VALUES (1,'LED', 'https://www.houseiq.pl/userdata/public/gfx/11868.jpg')
INSERT INTO components (id,name,imagePath) VALUES (2,'Outlet', 'https://www.houseiq.pl/userdata/public/gfx/11868.jpg')

END;
