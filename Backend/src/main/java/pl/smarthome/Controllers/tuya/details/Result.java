package pl.smarthome.Controllers.tuya.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Result {
    long active_time;
    int biz_type;
    String category;
    long create_time;
    String icon;
    String id;
    String ip;
    String lat;
    String local_key;
    String lon;
    String model;
    String name;
    boolean online;
    String owner_id;
    String product_id;
    String product_name;
    Status[] status;
    boolean sub;
    String time_zone;
    String uid;
    long update_time;
    String uuid;


}