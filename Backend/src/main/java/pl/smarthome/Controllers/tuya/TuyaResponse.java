package pl.smarthome.Controllers.tuya;

import lombok.ToString;

@ToString
class TuyaResponse {
    Result result;
    boolean success;
    double t;
    String tid;

    class Result {
        String access_token;
        double expire_time;
        String refresh_token;
        String uid;
    }
}
