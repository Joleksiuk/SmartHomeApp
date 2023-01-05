package pl.smarthome.Controllers.tuya.details;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommandList {
    private List<CodeValue> commands=new LinkedList<>();

    public String toJson(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
