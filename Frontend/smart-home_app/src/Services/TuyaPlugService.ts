import axios from "axios";


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
    
}
export default new TuyaLEDService();
