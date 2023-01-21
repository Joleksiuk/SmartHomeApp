import axios from "axios";
import AuthService from "../authorization/AuthService";
import { shelly_users_url } from "../urls";

const Shelly_URL = "http://localhost:8080/shelly_users";

export interface ShellyUser{
    id:number,
    server:string,
    auth_key:string
}
class ShellyService {
  
    createShellyUser(authKey: string, server: string) {

        return axios
        
        .post(Shelly_URL, {
            id:AuthService.getLoggedUser().id,
            auth_key: authKey,
            server: server
        })
        .catch(error => {
            console.log(error)
        });  
    }

    initShellyUser() {
        if(AuthService.getLoggedUser() !== null){
            axios.get(Shelly_URL+'/'+AuthService.getLoggedUser().id, {})
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
        return axios.delete(shelly_users_url+'/'+userId.toString,{})
        .then((response)=>console.log(response))
        .catch((error)=>{console.log(error);return;});
      }
}
export default new ShellyService();
