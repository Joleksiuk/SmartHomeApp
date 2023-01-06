package pl.smarthome.Controllers.tuya.devices;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.TuyaFunctions;
import pl.smarthome.Controllers.tuya.TuyaService;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("tuya_plug")
public class TuyaPlug {

    private final  TuyaService tuyaService;

    @Autowired
    public TuyaPlug(TuyaService tuyaService) {
        this.tuyaService = tuyaService;
    }

    @GetMapping("device={id}/switch={state}")
    public String switchPlug (@PathVariable String state, @PathVariable String id) {
        CodeValue codeValue=new CodeValue("switch_1",state);
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,id);
    }

    @PostMapping("multi/{id}")
    public String makeMultiCommands(@RequestBody List<CodeValue> codeValues,  @PathVariable String id){
        return tuyaService.multiCommandsRequest(codeValues,id);
    }

    @GetMapping("power_consumption/{id}")
    public String getPowerConsumption(@PathVariable String id){

        String path = "/v1.0/iot-03/energy/electricity/devices/nodes/statistics-trend?energy_action=consume&statisticsType=day&startTime=1672615088&endTime=1672960688device_ids="+id;
        Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"GET", "",new HashMap<>() );
        return result.toString();

    }

}
