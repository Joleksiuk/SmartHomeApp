package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Scene;
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

}