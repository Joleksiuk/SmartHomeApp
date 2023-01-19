import axios from "axios";
import AuthService from "../authorization/AuthService";
import { UserPermisson } from "../interfaces";
import { device_url, houseUser_url, house_url } from "../urls";

class DeviceService {
  
    getDeviceDto(deviceId:number) {

    return axios
    
    .get(device_url+'/'+deviceId , {})
    .then((response) => response.data)
    .then((data) => {
        return data;
    }).catch(error => {console.log(error)});
  }

  getDevicePermission(deviceId:string){

    return axios
    
    .get(houseUser_url+'/deviceId='+deviceId+"/userId="+AuthService.getLoggedUser().id+'/permission' , {})
    .then((response) => response.data)
    .then((data) => {
        return data ;
    }).catch(error => {console.log(error)});
    
  }

  getRoleOfHouseUser(houseid:string){
    return axios
    
    .get(houseUser_url+'/userId='+AuthService.getLoggedUser().id+'/houseId='+houseid+'/role' , {})
    .then((response) => response.data)
    .then((data) => {
        return data ;
    }).catch(error => {console.log(error)});
    
  }

  deleteDeviceFromHouse(houseId:number, deviceId:number){
    return axios
    .delete(house_url+'/'+houseId+'/'+deviceId)
    .then((response) => response.data)
    .then((data) => {
        return data ;
    }).catch(error => {console.log(error); return;});
  }

}
export default new DeviceService();
