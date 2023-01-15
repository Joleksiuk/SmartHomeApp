import axios from "axios";
import { json } from "stream/consumers";
import AuthService from "../authorization/AuthService";
import { CodeValue } from "../interfaces";


export enum State {
    On="on",
    Off="off"
}

const Shelly_URL = "http://localhost:8080/shelly";

class ShellyDuoService {

    makeShellyRequest=(deviceId: string, command:string, value: string)=>{
        let url = Shelly_URL+'/device='+deviceId+'/'+command+'='+value.toString() +'/'+AuthService.getLoggedUser().id
        return axios       
        .post(url, JSON.stringify(AuthService.getLoggedUser().id))
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
        let url = Shelly_URL+'/multi/'+deviceId +'/'+ AuthService.getLoggedUser().id
        let body = JSON.stringify(values)
        return axios       
        .post(url, body)
        .catch(error => {
            console.log(error)
        }); 
    }

    getDeviceStatus(deviceId: number){
        let url = Shelly_URL+'/'+ AuthService.getLoggedUser().id+'/'+deviceId+'/Status'
        return axios
        .get(url, {})
        .then(response => {
           return response.data as CodeValue[]
        }).catch(error => { console.log(error)}); 
    }
    
}
export default new ShellyDuoService();
