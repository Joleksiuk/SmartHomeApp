import axios from "axios";
import { inherits } from "util";
import ShellyService from "../Services/ShellyService";
import { shelly_users_url } from "../urls";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
  
    login(username: string, password: string) {

    return axios
    
      .post(API_URL + "signin", {
        username: username,
        password: password
      })
      .then(response => {
        if (response.data) {        
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(username: string, email: string, password: string) {
    let msg = {
      'username':username,
      'email':email,
      'role': [],
      'password':password
    }
    JSON.stringify(msg)
    return axios.post(API_URL + "signup",msg);
  }

  getLoggedUser() {
    const userStr = localStorage.getItem('user');
    if (userStr) return JSON.parse(userStr);
    return null;
  }
  isUserLoggedIn() {
    return this.getLoggedUser() !== null;
  }

}
export default new AuthService();
