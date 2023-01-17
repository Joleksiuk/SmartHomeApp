import axios from "axios";
import { DeviceDto } from "../interfaces";
import { commands_url, scene_url } from "../urls";

class SceneService {
  
    getScene(sceneId:string) {

        return axios.get(scene_url+'/'+sceneId,{})
        .then((response) => response.data)
        .then((data) => {
            return data;
        }).catch(error => { console.log(error)});
    }

    getSceneDevices(sceneId:string){
        return axios.get(scene_url+'/devices/'+sceneId,{})
        .then((response) => response.data)
        .then((data) => {
            return data;
        }).catch(error => { console.log(error)});
    }

    getDevicesToAdd(sceneId:string, houseId:string){
        return axios.get(scene_url+'/Add/houseId='+houseId+'/sceneId='+sceneId, {})
        .then((response) => response.data)
        .then((data) => {
            return data;
        }).catch(error => { console.log(error)});
    }

    getSceneProps(sceneId:string, deviceId:string){
       
        return axios.get(commands_url+'/sceneId='+sceneId+'/deviceId='+deviceId, {})
        .then((response) => response.data)
        .then((data) => {
            return data;
        }).catch(error => { });
    }

    putNewSceneProps(sceneId:string, device:DeviceDto){

        return axios.put(commands_url+'/new_props/sceneId='+sceneId,device)
        .then((data) => {
            console.log(data)
        }).catch(error => { console.log(error)});
    }

    deleteDeviceFromScene(sceneId:string, deviceId:string){
        return axios
        .delete(scene_url+'/'+sceneId+'/'+deviceId)
        .then((response) => response.data)
        .then((data) => {
            return data ;
        }).catch(error => {console.log(error)});
      }
    
    deleteScene(sceneId:string){
        return axios
        .delete(scene_url+'/'+sceneId+'/')
        .then((response) => response.data)
        .then((data) => {
            return data ;
        }).catch(error => {console.log(error)});
    }
}
export default new SceneService();
