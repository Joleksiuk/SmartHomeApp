import {Box, Button, Card, CardActionArea, CardContent, CardMedia, Grid, LinearProgress, Typography } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import AuthService from '../authorization/AuthService';
import {Device, DeviceDto, Scene, ComponentProp } from '../interfaces';
import SceneService from '../Services/SceneService';
import {scene_url } from '../urls';
import ShellyDuo from './shelly/ShellyDuo';
import TuyaLED from './tuya/TuyaLED';
import TuyaPlug from './tuya/TuyaPlug';

export default function SceneComponent() {

    const [scene, setScene]=useState<Scene>()
    const [sceneDevices,setSceneDevices]=useState<Array<DeviceDto>>()
    const [devicesToAdd, setDevicesToAdd]=useState<Array<DeviceDto>>();
    const [sceneReady,setSceneReady]=useState<Boolean>(false)
    const [devicesReady,setDevicesReady]=useState<Boolean>(false)
    const [addReady,setAddReady]=useState<Boolean>(false)
    const {id}=useParams()
 
    useEffect(() => {
        getCurrentScene()       
     }, []);

     useEffect(() => {
        getSceneDevices()
        getDevicesToAdd()
     }, [sceneReady]);

    const getCurrentScene = async()=>{
        if(id!==undefined){
            let response = await SceneService.getScene(id)
            setScene(response)
            setSceneReady(true)
        }
    }

    const getSceneDevices = async()=>{
        if(id!==undefined){
            let response = await SceneService.getSceneDevices(id);
            setSceneDevices(response)
            setDevicesReady(true)
        }
    }

    const getDevicesToAdd = async ()=>{
        if(id!==undefined && scene!==undefined){
            let response = await SceneService.getDevicesToAdd(id,scene?.houseId.toString())
            setDevicesToAdd(response)
            setAddReady(true)
        }
    }

    type ComponentMap = {
        [key: string]: React.ComponentType;
    };

    const componentMap: ComponentMap = {
        'Shelly duo': ShellyDuo,
        'Tuya Smart Plug': TuyaPlug,
        'Tuya Smart LED': TuyaLED,
   };

    const render =(componentName:string, componentProps:any) =>{
        const Component = componentMap[componentName];
        if (!Component) return null;
        return <Component {...componentProps}/>;
      }

    const showPanel=(device:DeviceDto)=>{
        if(device!==undefined){
            return(
                <div>
                    {devicesReady  && id!=undefined&&
                        render(device.componentName , new ComponentProp(device,id))
                    }
                </div>
            );
        }    
    } 

    const handleAddDeviceToScene =(device:Device)=>{
        axios.get(scene_url+'/Add/deviceId='+device.id+'/sceneId='+id, {}).then((response) => console.log(response.data)).catch(error => {console.log(error);}); 
    }

    const executeScene=()=>{
        axios.get(scene_url+'/SetScene/'+id+'/'+AuthService.getLoggedUser().id, {}).then((response) => console.log(response.data)).catch(error => {console.log(error);}); 
    }
    return (
        <div>
        {(sceneReady && addReady && devicesReady) ?
        <Grid container spacing={3}>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h4" >Welcome to scene: {scene?.name}</Typography>
            </Grid>

            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Scene Devices</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>
                <Grid container justifyContent="center" spacing={3}>
                {sceneDevices?.map((device)=>
                    <Grid item>
                        {showPanel(device)}
                    </Grid>
                )}
                </Grid>
            </Grid> 
            <Grid justifyContent="center" container item xs={12}>
            {devicesToAdd?.length!=0 && 
              <> 
                <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h4" >Devices to add</Typography>
                </Grid> 
                <br></br>
                <br></br> 
                <br></br>
                <Grid justifyContent="center" container item xs={12}>
                    <Grid container justifyContent="center" spacing={3}>
                    {devicesToAdd?.map((device)=>
                        <Button onClick={()=>handleAddDeviceToScene(device)}>
                            <Grid item>
                                <Card>
                                    <CardActionArea>
                                        <CardMedia
                                        src ={device.imagePath}
                                        component="img"
                                        height="150"
                                        />
                                        <CardContent>
                                        <Typography gutterBottom variant="h5" component="div">
                                            {device.name } 
                                        </Typography>
                                        </CardContent>
                                    </CardActionArea>
                                </Card>
                            </Grid>
                        </Button>
                    )}
                    </Grid>
                </Grid> 
              </>
            }
          </Grid> 
            <Grid justifyContent="center" container item xs={12}>
                <Button variant="contained" onClick={executeScene}>Execute Scene</Button>           
            </Grid>   

        </Grid>     
        :
        <Box sx={{ width: '100%' }}>
            <LinearProgress />
        </Box>
    }
    </div>
    
      );
}