import axios from "axios";
import AuthService from "../authorization/AuthService";
import { Component, DeviceDto, HouseDto, Scene } from "../interfaces";
import { component_url, houseUser_url, house_url, scene_url } from "../urls";

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
        return axios.get(house_url+'/'+houseId+'/'+AuthService.getLoggedUser().id +'/data' , {})
        .then((response) => response.data)
        .then((data) => {
            return data as HouseDto;
        } )
    }

    getHomeUserRole(houseId:string){
        return axios.get(houseUser_url+'/userId='+AuthService.getLoggedUser().id+'/houseId='+houseId+'/role' , {})
        .then((response) => response.data)
        .then((data) => {
            return data ;
        } )   
    } 

    deleteHome(houseId: number){
        return axios.delete(house_url+'/'+houseId)
        .then((response) => response.data)
        .then((data) => {
            return data ;
        } )  
        .catch(error => { console.log(error);   return;}); 
    }
}
export default new ShellyService();
