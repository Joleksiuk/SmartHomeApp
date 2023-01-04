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

