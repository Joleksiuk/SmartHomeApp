package pl.smarthome.Controllers.tuya.details;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CodeValue<T>{
    private String code;
    private T value;
}
