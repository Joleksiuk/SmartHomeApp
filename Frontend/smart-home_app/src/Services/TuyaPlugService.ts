import axios from "axios";
import { CodeValue } from "../interfaces";


const tuya_url = "http://localhost:8080/tuya_plug";

class TuyaLEDService {

    makeTuyaRequest=(deviceId: string, command:string, value: string)=>{
        let url = tuya_url+'/device='+deviceId+'/'+command+'='+value.toString()     
        return axios       
        .get(url, {})
        .catch(error => {
            console.log(error)
        });  
    }
  
    switchPlug(deviceId: string, state: boolean) {

        this.makeTuyaRequest(deviceId,"switch",state.toString()); 
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
