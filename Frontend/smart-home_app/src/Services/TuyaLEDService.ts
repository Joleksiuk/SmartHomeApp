import axios from "axios";
import AuthService from "../authorization/AuthService";
import { CodeValue } from "../interfaces";


const tuya_url = "http://localhost:8080/tuya_led";

class TuyaLEDService {

    makeTuyaRequest=(deviceId: string, command:string, value: string)=>{
       
        let url = tuya_url+'/device='+deviceId+'/'+command+'='+value       
        let msg={
            "userId":AuthService.getLoggedUser().id
        }  
        return axios       
        .post(url, JSON.stringify(msg))
        .catch(error => {
            console.log(error)
        });  
    }
  
    switchLed(deviceId: string, state: boolean) {

        this.makeTuyaRequest(deviceId,'switch',state.toString()); 
    }

    changeColor(deviceId: string, value: string) {

        this.makeTuyaRequest(deviceId,'color',value); 
    }

    changeIntensity(deviceId: string, value: string) {

        this.makeTuyaRequest(deviceId,'intensity',value); 
    }

    getDeviceDetails(deviceId: string){
        let url = tuya_url+'/device='+deviceId
        let msg={
            "userId":AuthService.getLoggedUser().id
        }  
        return axios
        .post(url, JSON.stringify(msg))
        .then(response => {
           console.log(response)
        })
        .catch(error => {
            console.log(error)
        }); 
    }

    makeMultiRequest(values:Array<CodeValue>,deviceId: string){
        let url = tuya_url+'/multi/'+deviceId+'/'+AuthService.getLoggedUser().id  
        let body = JSON.stringify(values)
        return axios       
        .post(url, body)
        .catch(error => {
            console.log(error)
        }); 
    }
    
}
export default new TuyaLEDService();
