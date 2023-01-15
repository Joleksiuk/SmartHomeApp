export interface House{
    id:number,
    ownerId:number,
    name:string,
}

export interface Component{
    id:number,
    name:string,
    imagePath:string,
    brand:string
}

export interface Device{
    id:number,
    componentId:number,
    houseId:number,
    specificId:string
    name:string
}

export interface HSV{
    h:number,
    s:number,
    v:number
}

export interface DeviceDto{
    id:number,
    componentId:number,
    houseId:number,
    specificId:string
    name:string,
    componentName:string,
    imagePath:string,
    brand:string,
    props:CodeValue[],
    permissions:RolePermission[]
}

export interface DevicePermissionDto{
    id:number,
    name:string,
    componentName:string,
    imagePath:string,
    brand:string,
    permissions:PermissionDto[]
}

export interface PermissionDto{
    canSee:string,
    canControl:string,
    role:string
}


export class ComponentProp{

    device?:DeviceDto;
    sceneId?:string;
    constructor(device:DeviceDto, sceneId:string){
        this.device=device;
        this.sceneId=sceneId;
    }
  }

export interface Secret{
    userId:number;
}

export interface Scene{
    id:number,
    houseId:number,
    ownerId:number,
    name:string
}

export interface Command{
    id:number,
    componentId:number,
    endpoint:string,
    deviceId:number
    sceneId:number
}

export class CodeValue{
    code:string;
    value:string;
    constructor(code:string,value:string){
        this.code=code;
        this.value=value;
    }
}

export interface CommandDto{
    deviceId:number,
    code:string,
    value:any
}

export interface User{
    id:number,
    username:string,
    email:string
}

export interface HouseUserDto{
    userId:number,
    houseId:number,
    username:string,
    email:string,
    role:string
}

export interface HouseUser{
    id:HouseUserId,
    role:string
}

export interface HouseUserId{
    userId:number,
    houseId:number
}

export interface RolePermissionDto{
    id:number,
    deviceId:number,
    deviceName:string,
    role:string,
    canSee:string,
    canControl:string
}

export interface RolePermission{
    id:number,
    deviceId:number,
    role:string,
    canSee:string,
    canControl:string
}

export interface HouseDto{
    house?:House,
    components?:Component[],
    devices?: DeviceDto[],
    scenes?: Scene[]
}

export interface UserPermisson{
    userId?:number,
    deviceId?:number,
    canSee?:Boolean,
    canControl?:Boolean,
}

