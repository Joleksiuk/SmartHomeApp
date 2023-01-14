import axios from "axios";
import AuthService from "../authorization/AuthService";
import { CodeValue } from "../interfaces";


const tuya_url = "http://localhost:8080/tuya_led";

class TuyaLEDService {

    makeTuyaRequest=(deviceId: number, command:string, value: string)=>{
       
        let url = tuya_url+'/userId='+AuthService.getLoggedUser().id+'/device='+deviceId+'/'+command+'='+value   
        console.log(url)    
        return axios       
        .post(url, {})
        .catch(error => {
            console.log(error)
        });  
    }
  
    switchLed(deviceId: number, state: boolean) {
        
        let value='false';
        if(state==true){
            value ='true';
        }
        this.makeTuyaRequest(deviceId,'switch',value); 
    }

    changeColor(deviceId: number, value: string) {

        this.makeTuyaRequest(deviceId,'color',value.slice(1)); 
    }

    changeIntensity(deviceId: number, value: string) {

        this.makeTuyaRequest(deviceId,'intensity',value); 
    }

    getDeviceDetails(deviceId: number){
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
   
    getDeviceStatus(deviceId: number){
        let url = tuya_url+'/'+AuthService.getLoggedUser().id+'/'+deviceId+'/Status'
        return axios
        .get(url)
        .then(response => {
           return response.data as CodeValue[]
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
