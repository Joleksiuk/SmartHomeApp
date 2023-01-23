import axios from "axios";
import AuthService from "../authorization/AuthService";
import { tuya_url, tuya_users_url } from "../urls";

export interface TuyaUser{
  id:number,
  server:string,
  accessId:string,
  secretKey:string
}

class TuyaService {
  
    createTuyaUser(accessId: string, server: string, secretKey:string) {

    return axios
    
      .post(tuya_users_url, {
        id:AuthService.getLoggedUser().id,
        accessId: accessId,
        server: server,
        secretKey: secretKey
      })
      .then((response) => response.data)
      .then((data) => {
          return data ;
      } ) 
      .catch(error => {
        console.log(error)
      });
  }

  doesTuyaUserExist() {
    if(AuthService.getLoggedUser() !== null){
        return true;
    }
    return false;
  }

  getTuyaUser(){

    if(AuthService.getLoggedUser() !== null){
      
      axios.get(tuya_users_url+'/'+AuthService.getLoggedUser().id.toString(), {})
      .then(response => { 
        if (response.data) { 
          return response.data as TuyaUser}})
      .catch(error => {
        console.log(error)
        return null; 
      });
    }
    return null;   
  }

  deleteTuyaUser(userId:number){
    console.log(userId);
    return axios.delete(tuya_users_url+'/'+userId.toString(),{})
    .then((response)=>console.log(response))
    .catch((error)=>{console.log(error);return;});
  }

  verifyTuyaDeviceId(deviceSpecificId: string){
    console.log('dupa')
    return axios.get(tuya_url+'/'+AuthService.getLoggedUser().id.toString()+'/'+deviceSpecificId,{})
    .then((response) => response.data)
    .then((data) => {
            return data as boolean;
    }).catch(error => {console.log(error)});
  }
}
export default new TuyaService();
