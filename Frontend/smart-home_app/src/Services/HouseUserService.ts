import axios from "axios";
import AuthService from "../authorization/AuthService";
import { HouseUserDto } from "../interfaces";
import { houseUser_url } from "../urls";

class HouseUserService {
  
    addHouseUser(houseId:string, username:string) {

        return axios
       .post(houseUser_url+ '/houseId='+houseId+'/username='+username,{})
       .then((response) => response.data)
        .then((data) => {
            return data ;
        }).catch(error => {console.log(error)});
    }

    setUserRole (value:any, userId:number, houseId:string){
        let msg={
            'userId':userId,
            'houseId':houseId,
            'role':value
        }
        return axios.put(houseUser_url,msg)
        .then((response) => {console.log(response.data)})
        .catch((error) => console.log(error));
      };

      fetchHouseUsers(houseId:string){
        return axios.get<HouseUserDto[]>(houseUser_url+ '/houseId=' + houseId)
        .then((response) => response.data)
        .then((data) => {
            return data ;
        }).catch((error) => {
            console.log(error);return [];}
            );
      }
}
export default new HouseUserService();
