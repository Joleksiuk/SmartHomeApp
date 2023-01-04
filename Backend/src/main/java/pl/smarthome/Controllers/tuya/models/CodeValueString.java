package pl.smarthome.Controllers.tuya.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CodeValueString {

    private String code;
    private String value;

    public CodeValueString(@JsonProperty("code") String code, @JsonProperty("value") String value) {
        this.code=code;
        this.value=value;
    }
    public String toJson(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
