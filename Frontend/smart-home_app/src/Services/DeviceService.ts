import axios from "axios";
import AuthService from "../authorization/AuthService";
import { UserPermisson } from "../interfaces";
import { device_url, houseUser_url } from "../urls";

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

  deleteDeviceFromHouse(houseId:string, deviceId:string){
    return axios
    .delete(device_url)
    .then((response) => response.data)
    .then((data) => {
        return data ;
    }).catch(error => {console.log(error)});
  }


}
export default new DeviceService();
