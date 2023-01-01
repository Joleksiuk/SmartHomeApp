import axios from "axios";
import AuthService from "../authorization/AuthService";

const Shelly_URL = "http://localhost:8080/shelly_users";

class ShellyService {
  
    createShellyUser(authKey: string, server: string) {

    return axios
    
      .post(Shelly_URL, {
        auth_key: authKey,
        server: server
      })
      .catch(error => {
        console.log(error)
      });  
  }

  getShellyUser() {
    if(AuthService.getLoggedUser() !== null){
        axios.get(Shelly_URL+'/'+AuthService.getLoggedUser().id, {})
        .then(response => { 
            if (response.data) {
            localStorage.setItem("shelly_user", JSON.stringify(response.data));
          }
          return response.data;})
        .catch(error => {console.log(error)})      
    }
    return null;
  }
  
}
export default new ShellyService();