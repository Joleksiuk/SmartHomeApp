import axios from "axios";
import { scene_url } from "../urls";

class SceneService {
  
    getScene(sceneId:string) {

        return axios.get(scene_url+'/'+sceneId,{})
        .then((response) => response.data)
        .then((data) => {
            return data;
        }).catch(error => { });
    }

    getSceneDevices(sceneId:string){
        return axios.get(scene_url+'/devices/'+sceneId,{})
        .then((response) => response.data)
        .then((data) => {
            return data;
        }).catch(error => { });
    }

    getDevicesToAdd(sceneId:string, houseId:string){
        return axios.get(scene_url+'/Add/houseId='+houseId+'/sceneId='+sceneId, {})
        .then((response) => response.data)
        .then((data) => {
            return data;
        }).catch(error => { });
    }
}
export default new SceneService();
