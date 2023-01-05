package pl.smarthome.Controllers.tuya.devices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.smarthome.Controllers.tuya.TuyaService;
import pl.smarthome.Controllers.tuya.details.CodeValue;

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
}
