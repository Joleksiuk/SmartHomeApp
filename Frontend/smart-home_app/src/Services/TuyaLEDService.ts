import axios from "axios";
import { CodeValue } from "../interfaces";


const tuya_url = "http://localhost:8080/tuya_led";

class TuyaLEDService {

    makeTuyaRequest=(deviceId: string, command:string, value: string)=>{
       
        let url = tuya_url+'/device='+deviceId+'/'+command+'='+value   
        console.log(url)
        return axios       
        .get(url, {})
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

        return axios
        .get(url, {})
        .then(response => {
           console.log(response)
        })
        .catch(error => {
            console.log(error)
        }); 
    }

    makeMultiRequest(values:Array<CodeValue>,deviceId: string){
        let url = tuya_url+'/multi/'+deviceId  
        let body = JSON.stringify(values)
        return axios       
        .post(url, body)
        .catch(error => {
            console.log(error)
        }); 
    }
    
}
export default new TuyaLEDService();
