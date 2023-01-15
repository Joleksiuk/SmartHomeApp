import axios from "axios";
import { Component, DeviceDto, HouseDto, Scene } from "../interfaces";
import { component_url, house_url, scene_url } from "../urls";

class ShellyService {
  
    getCurrentDevices(houseId:string) {

        return axios
        .get(house_url+'/houseId='+houseId , {})
        .then((response) => response.data)
        .then((data) => {
            return data as DeviceDto[]     
        } )  
        .catch(error => { console.log(error) }); 
    }

    getAllComponents(){
        axios.get(component_url+'/all' , {})
        .then((response) => response.data)
        .then((data) => {
            return data
        })
        .catch(error => { console.log(error) }); 
    }

    getHomeScenes(houseId:string){
        axios.get(scene_url+'/houseId='+houseId , {})
        .then((response) => response.data)
        .then((data) => {
            return data as Scene[]
        })
        .catch(error => { console.log(error) }); 
    }

    getCurrentHome(houseId:string){
        axios.get(house_url+'/houseId='+houseId , {})
        .then((response) => response.data)
        .then((data) => {
            return data;     
        } )
    }

    getHomeData(houseId:string){
        return axios.get(house_url+'/'+houseId+'/data' , {})
        .then((response) => response.data)
        .then((data) => {
            return data as HouseDto;
        } )
    }
    
}
export default new ShellyService();
