package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Models.Scene;
import pl.smarthome.Models.dtos.DeviceDto;
import pl.smarthome.Services.SceneService;

import java.util.List;

@RestController
@RequestMapping("scenes")
public class SceneController {

    private final SceneService sceneService;

    @Autowired
    public SceneController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @PostMapping
    public void createScene(@RequestBody Scene scene) {
        sceneService.createScene(scene);
    }

    @PutMapping
    public void updateScene(@RequestBody Scene house) {
        sceneService.updateScene(house);
    }

    @DeleteMapping
    public void DeleteSceneById(@RequestBody Long id) {
        sceneService.deleteSceneById(id);
    }

    @GetMapping("{id}")
    public Scene findSceneById(@PathVariable Long id) {
        return sceneService.findSceneBySceneId(id).orElse(null);
    }

    @GetMapping("ownerId={id}")
    public List<Scene> findHouseByOwnerId(@PathVariable Long id) {
        return sceneService.getScenesByOwnerId(id);
    }

    @GetMapping("houseId={id}")
    public List<Scene> findHouseByHouseId(@PathVariable Long id) {
        return sceneService.getScenesByHouseId(id);
    }

    @GetMapping("devices/{id}")
    public List<DeviceDto> getDevicesBySceneId(@PathVariable Long id) {
        return sceneService.getDevicesBySceneId(id);
    }

    @GetMapping("Add/houseId={houseId}/sceneId={sceneId}")
    public List<DeviceDto> getHouseDevicesToAddBySceneId(@PathVariable Long sceneId, @PathVariable Long houseId){
        return sceneService.getHouseDevicesToAddBySceneId(houseId,sceneId);
    }

    @GetMapping("Add/deviceId={deviceId}/sceneId={sceneId}")
    public void addDeviceToScene(@PathVariable Long deviceId,@PathVariable Long sceneId){
        sceneService.addDeviceToScene(deviceId,sceneId);
    }

    @GetMapping("SetScene/{sceneId}/{userId}")
    public void setScene(@PathVariable Long sceneId, @PathVariable Long userId){
        sceneService.setScene(sceneId,userId);
    }
    @GetMapping("props/{sceneId}/{deviceId}")
    public List<CodeValue> getDeviceSceneProps(@PathVariable Long sceneId, @PathVariable Long deviceId){
        return sceneService.getDeviceSceneProps(sceneId,deviceId);
    }

    @GetMapping("props/default/{componentId}")
    public List<CodeValue> getDefaultProps(@PathVariable Long componentId){
        return sceneService.getDefaultComponentProps(componentId);
    }


}