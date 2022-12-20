package pl.smarthome.Controllers.tuya;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CodeValue {

    private String code;
    private Boolean value;

    public CodeValue( @JsonProperty("code") String code, @JsonProperty("value") Boolean value) {
        this.code=code;
        this.value=value;
    }
    public String toJson(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
