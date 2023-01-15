import axios from "axios";
import AuthService from "../authorization/AuthService";
import { CodeValue } from "../interfaces";
import { tuya_led_url, tuya_plug_url } from "../urls";


class TuyaLEDService {

    makeTuyaRequest=(deviceId: number, command:string, value: string)=>{
        let url = tuya_plug_url+'/device='+deviceId.toString()+'/'+command+'='+value.toString()+'/'+AuthService.getLoggedUser().id     
        return axios       
        .post(url, {})
        .catch(error => {console.log(error)});  
    }
  
    switchPlug(deviceId: number, state: boolean) {
        this.makeTuyaRequest(deviceId,"switch",state.toString()); 
    }
    
    makeMultiRequest(values:Array<CodeValue>,deviceId: number){
        let url = tuya_plug_url+'/multi/'+deviceId.toString()+'/'+AuthService.getLoggedUser().id       
        let body = JSON.stringify(values)
        return axios       
        .post(url, body)
        .catch(error => { console.log(error) }); 
    }

    getDeviceStatus(deviceId: number){
        let url = tuya_led_url+'/'+AuthService.getLoggedUser().id+'/'+deviceId.toString()+'/Status'
        return axios
        .get(url)
        .then(response => {
           return response.data as CodeValue[]
        })
        .catch(error => { console.log(error) }); 
    } 
}

export default new TuyaLEDService();
