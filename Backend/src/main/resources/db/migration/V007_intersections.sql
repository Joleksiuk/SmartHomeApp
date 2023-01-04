BEGIN;

CREATE TABLE IF NOT EXISTS public.device_permission
(
    deviceId bigint REFERENCES devices(id),
    userId bigint REFERENCES users(id),
    canControl text NOT NULL,
    canSee text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.scene_command
(
    deviceId bigint REFERENCES devices(id),
    commandId bigint REFERENCES commands(id)
);

CREATE TABLE IF NOT EXISTS public.device_house
(
    deviceId bigint REFERENCES devices(id),
    houseId bigint REFERENCES houses(id)
);

CREATE TABLE IF NOT EXISTS public.command_device
(
    deviceId bigint REFERENCES devices(id),
    commandId bigint REFERENCES commands(id)
);

CREATE TABLE IF NOT EXISTS public.house_user
(
    userId bigint REFERENCES users(id),
    houseId bigint REFERENCES houses(id),
    role text NOT NULL
);


END;