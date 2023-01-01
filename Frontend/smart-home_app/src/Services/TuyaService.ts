import axios from "axios";
import AuthService from "../authorization/AuthService";

const Tuya_URL = "http://localhost:8080/tuya_users";

class TuyaService {
  
    createTuyaUser(accessId: string, server: string, secretKey:string) {

    return axios
    
      .post(Tuya_URL, {
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

  getTuyaUserById(id:number){

    return axios.get(Tuya_URL+'/'+id.toString(), {})
      .catch(error => {
        console.log(error)
      });
  }
}
export default new TuyaService();
