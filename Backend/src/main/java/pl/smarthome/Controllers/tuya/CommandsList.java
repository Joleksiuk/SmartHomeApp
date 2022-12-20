package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommandsList {

    private List<CodeValue> commands=new LinkedList<>();

    public void addCommand(CodeValue codeValue) {
        commands.add(codeValue);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
