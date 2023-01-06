import axios from "axios";
import { CodeValue } from "../interfaces";


export enum State {
    On="on",
    Off="off"
}

const Shelly_URL = "http://localhost:8080/shelly";

class ShellyDuoService {

    makeShellyRequest=(deviceId: string, command:string, value: string)=>{
        let url = Shelly_URL+'/device='+deviceId+'/'+command+'='+value.toString()     
        return axios       
        .post(url, {})
        .catch(error => {
            console.log(error)
        });  
    }
  
    switchBulb(deviceId: string, state: State) {

        this.makeShellyRequest(deviceId,"switch",state.toString()); 
    }

    changeBrightness(deviceId: string, value: string) {

        this.makeShellyRequest(deviceId,"brightness",value); 
    }

    changeTemperature(deviceId: string, value: string) {

        this.makeShellyRequest(deviceId,"temp",value); 
    }

    changeWhiteness(deviceId: string, value: string) {

        this.makeShellyRequest(deviceId,"white",value); 
    }

    makeMultiRequest(deviceId: string, values: Array<CodeValue>){
        let url = Shelly_URL+'/multi/'+deviceId  
        let body = JSON.stringify(values)
        return axios       
        .post(url, body)
        .catch(error => {
            console.log(error)
        }); 
    }

    getDeviceStatus(deviceId: string){
        let url = Shelly_URL+'/'+deviceId

        return axios
        .get(url, {})
        .then(response => {
           console.log(response)
        })
        .catch(error => {
            console.log(error)
        }); 
    }
    
}
export default new ShellyDuoService();
