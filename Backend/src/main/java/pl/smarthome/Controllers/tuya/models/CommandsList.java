package pl.smarthome.Controllers.tuya.models;

import com.google.gson.Gson;
import lombok.*;
import pl.smarthome.Controllers.tuya.models.CodeValueBoolean;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommandsList {

    private List<CodeValueBoolean> commands=new LinkedList<>();

    public void addCommand(CodeValueBoolean codeValueBoolean) {
        commands.add(codeValueBoolean);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
