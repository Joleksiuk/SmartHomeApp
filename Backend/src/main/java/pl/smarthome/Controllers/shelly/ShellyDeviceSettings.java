package pl.smarthome.Controllers.shelly;

import lombok.Getter;

import java.util.List;

@Getter
public class ShellyDeviceSettings {
    boolean isok;
    Data data;
}


class DeviceSettings {
    String time;
    boolean discoverable;
    boolean tz_dst_auto;
    String name;
    Mqtt mqtt;
    HwInfo hwinfo;
    double lng;
    double lat;
    String fw;
    Coiot coiot;
    List<Light> lights;
    String pin_code;
    WifiSta wifi_sta;
    WifiSta wifi_sta1;
    Cloud cloud;
    boolean tz_dst;
    int tz_utc_offset;
    boolean tzautodetect;
    Device device;
    String mode;
    int transition;
    NightMode night_mode;
    WifiAp wifi_ap;
    Sntp sntp;
    long unixtime;
    String timezone;
    Login login;
    BuildInfo build_info;
    String _updated;
    Actions actions;
}

class Mqtt {
    boolean enable;
    String server;
    String user;
    String id;
    int reconnect_timeout_max;
    int reconnect_timeout_min;
    boolean clean_session;
    int keep_alive;
    int max_qos;
    boolean retain;
    int update_period;
}

class HwInfo {
    String hw_revision;
    int batch_id;
}

class Coiot {
    int update_period;
}

class Light {
    String name;
    boolean ison;
    int brightness;
    int white;
    int temp;
    String default_state;
    int auto_on;
    int auto_off;
    boolean schedule;
    String out_on_url;
    String out_off_url;
    List<Object> schedule_rules;
}

class WifiSta {
    boolean enabled;
    String ssid;
    String ipv4_method;
    String ip;
    String gw;
    String mask;
    String dns;
}

class Cloud {
    boolean enabled;
    boolean connected;
}

class Device {
    String type;
    String mac;
    String hostname;
    int num_outputs;
}

class NightMode {
    boolean enabled;
    String start_time;
    String end_time;
    int brightness;
}

class WifiAp {
    boolean enabled;
    String ssid;
    String key;
}

class Sntp {
    String server;
    boolean enabled;
}

class Login {
    boolean enabled;
    boolean unprotected;
    String username;
}

class BuildInfo {
    String build_id;
    String build_timestamp;
    String build_version;
}

class Actions {
    boolean active;
    List<String> names;
}

class Data {
    DeviceSettings device_settings;
}


