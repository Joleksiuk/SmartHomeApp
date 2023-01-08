package pl.smarthome.Controllers.tuya.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TuyaStatusResponse {
    private List<CodeValue> result;
    private boolean success;
    private long t;
    private String tid;
}
