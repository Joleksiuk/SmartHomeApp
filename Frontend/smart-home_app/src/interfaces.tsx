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

