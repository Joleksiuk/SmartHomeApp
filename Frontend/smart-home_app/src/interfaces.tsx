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

export interface DeviceDto{
    id:number,
    componentId:number,
    houseId:number,
    specificId:string
    name:string,
    componentName:string,
    imagePath:string,
    brand:string,
}


export interface ComponentProp{

    pp?: CodeValue[]
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

export interface CodeValue{
    code:string,
    value:string
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


