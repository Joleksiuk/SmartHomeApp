package pl.smarthome.Controllers.tuya.details;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class DeviceDetails {
    public Result result;
    public boolean success;
    public long t;
    public String tid;

}


