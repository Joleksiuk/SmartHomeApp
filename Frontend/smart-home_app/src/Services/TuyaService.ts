import axios from "axios";
import AuthService from "../authorization/AuthService";

const Tuya_URL = "http://localhost:8080/tuya_users";


export interface TuyaUser{
  id:number,
  server:string,
  accessId:string,
  secretKey:string
}

class TuyaService {
  
    createTuyaUser(accessId: string, server: string, secretKey:string) {

    return axios
    
      .post(Tuya_URL, {
        id:AuthService.getLoggedUser().id,
        accessId: accessId,
        server: server,
        secretKey: secretKey
      })
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
      
      axios.get(Tuya_URL+'/'+AuthService.getLoggedUser().id.toString(), {})
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
}
export default new TuyaService();
