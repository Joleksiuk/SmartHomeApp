BEGIN;

CREATE TABLE IF NOT EXISTS public.device_permission
(
    device_id bigint REFERENCES devices(id),
    user_id bigint REFERENCES users(id),
    can_control text NOT NULL,
    can_see text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.scene_command
(
    device_id bigint REFERENCES devices(id),
    command_id bigint REFERENCES tuyaCommands(id)
);

CREATE TABLE IF NOT EXISTS public.command_device
(
    device_id bigint REFERENCES devices(id),
    command_id bigint REFERENCES tuyaCommands(id)
);

CREATE TABLE IF NOT EXISTS public.house_user
(
    user_id bigint REFERENCES users(id),
    house_id bigint REFERENCES houses(id),
    role text NOT NULL
);


END;