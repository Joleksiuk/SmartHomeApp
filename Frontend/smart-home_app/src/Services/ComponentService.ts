import axios from "axios";
import { useState } from "react";

const component_url = "http://localhost:8080/component";

export interface Component{
    id:number,
    name:string,
    imagePath:string,
    brand:string
}

class ComponentService {
  
  getComponentByName(name:string){

    let component :Component = {
        id: 0,
        name: '',
        imagePath: '',
        brand:''
    };

    const component_url = "http://localhost:8080/component";
    axios.get(component_url+'/name='+name, {})
      .then((response) => response.data)
      .then((data) => {return data})
      .catch(error => {
        console.log(error)
      });

    return component
  }
}
export default new ComponentService();
