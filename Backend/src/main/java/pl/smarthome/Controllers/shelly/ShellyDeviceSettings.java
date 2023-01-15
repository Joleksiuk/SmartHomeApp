package pl.smarthome.Controllers.shelly;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShellyDeviceSettings {
    public boolean isok;
    public Data data;
}
class Data {
    public boolean online;
    public DeviceStatus device_status;
    public int uptime;
    public int ram_free;
    public List<Light> lights;
    public boolean has_update;
    public String serial;
    public WifiSta wifi_sta;
    public Cloud cloud;
    public int fs_free;
    public int cfg_changed_cnt;
    public ActionsStats actions_stats;
    public GetInfo getinfo;
    public Mqtt mqtt;
    public int ram_total;
    public int fs_size;

    class DeviceStatus {
        public String mac;
        public Update update;
        public String time;
        public String _updated;
        public int unixtime;
        public List<Meter> meters;
    }

    class Update {
        public String status;
        public boolean has_update;
        public String new_version;
        public String old_version;
    }

    class Meter {
        public double power;
        public boolean is_valid;
        public int timestamp;
        public List<Double> counters;
        public int total;
    }

    class Light {
        public boolean ison;
        public boolean has_timer;
        public int timer_started;
        public int timer_duration;
        public int timer_remaining;
        public int brightness;
        public int white;
        public int temp;
    }

    class WifiSta {
        public boolean connected;
        public String ssid;
        public String ip;
        public int rssi;
    }

    class Cloud {
        public boolean enabled;
        public boolean connected;
    }

    class ActionsStats {
        public int skipped;
    }

    class GetInfo {
        public FwInfo fw_info;
    }

    class FwInfo {
        public String device;
        public String fw;
    }
    class Mqtt {
        public boolean connected;
    }
}

