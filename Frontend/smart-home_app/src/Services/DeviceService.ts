import axios from "axios";
import AuthService from "../authorization/AuthService";
import { device_url } from "../urls";

class DeviceService {
  
    getDeviceDto(deviceId:number) {

    return axios
    
    .get(device_url+'/'+deviceId , {})
    .then((response) => response.data)
    .then((data) => {
        return data;
    }).catch(error => {console.log(error)});
  }
}
export default new DeviceService();
