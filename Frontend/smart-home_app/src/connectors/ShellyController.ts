import axios from "axios";

const API_URL = "http://192.168.0.59/light/0?turn=toggle";

class ShellyController {
  
    toggleLight() {
        return axios.post(API_URL).then(response => {console.log(response)});
      }
}
export default new ShellyController();
