import axios from "axios";
import AuthService from "../authorization/AuthService";
import { shelly_url, shelly_users_url } from "../urls";

export interface ShellyUser{
    id:number,
    server:string,
    auth_key:string
}
class ShellyService {
  
    createShellyUser(authKey: string, server: string) {

        return axios
        
        .post(shelly_users_url, {
            id:AuthService.getLoggedUser().id,
            server: server,
            auth_key: authKey
           
        })
        .then((response) => response.data)
        .then((data) => {
            return data ;
        } ) 
        .catch(error => {
            console.log(error)
        });  
    }

    initShellyUser() {
        if(AuthService.getLoggedUser() !== null){
            axios.get(shelly_users_url+'/'+AuthService.getLoggedUser().id, {})
            .then(
                response => { 
                return response.data as ShellyUser;});
               
        }
        return null;
     }

    getLoggedShellyUser() {
        const userStr = localStorage.getItem('shelly_user');
        if (userStr) return JSON.parse(userStr);
        return null;
    }
    
    deleteShellyUser(userId:number){
        return axios.delete(shelly_users_url+'/'+userId.toString(),{})
        .then((response)=>console.log(response))
        .catch((error)=>{console.log(error);return;});
      }

    verifyShellyDeviceId(deviceSpecificId: string){
    console.log('dupa')
    return axios.get(shelly_url+'/verify/'+AuthService.getLoggedUser().id.toString()+'/'+deviceSpecificId,{})
    .then((response) => response.data)
    .then((data) => {
            return data as boolean;
    }).catch(error => {console.log(error)});
    }
}
export default new ShellyService();
