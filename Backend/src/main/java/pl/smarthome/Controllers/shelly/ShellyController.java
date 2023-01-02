package pl.smarthome.Controllers.shelly;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("shelly")
public class ShellyController {

    //e8db84db500b1
    @GetMapping("device={id}/switch={state}")
    public void switchBulb(@PathVariable String id, @PathVariable Boolean state){
        JsonObject body = ShellyService.primaryBody();
        body.addProperty("id", id);
        body.addProperty("turn", "off");
        ShellyService.execute("/device/light/control","POST", body.toString(),new HashMap<>() );
    }
}
