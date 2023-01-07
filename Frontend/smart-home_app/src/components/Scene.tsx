import { Button, Card, CardActionArea, CardContent, CardMedia, Grid, Typography } from '@mui/material';
import axios from 'axios';
import React, { ReactNode, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import AuthService from '../authorization/AuthService';
import { CommandDto, Device, Scene } from '../interfaces';
import {scene_url } from '../urls';
import ShellyDuo, { ShellyDuoProps } from './shelly/ShellyDuo';
import TuyaLED from './tuya/TuyaLED';
import TuyaPlug from './tuya/TuyaPlug';

export default function SceneComponent() {

    const [scene, setScene]=useState<Scene>()
    const [sceneDevices,setSceneDevices]=useState<Array<Device>>()
    const [deviceCommandMap,setDeviceCommandMap]=useState<Map<number,Array<CommandDto>>>()
    const [commands, setCommands]=useState<Array<CommandDto>>();
    const [devicesToAdd, setDevicesToAdd]=useState<Array<Device>>();

    const [sceneReady,setSceneReady]=useState<Boolean>(false)
    const [devicesReady,setDevicesReady]=useState<Boolean>(false)
    const [addReady,setAddReady]=useState<Boolean>(false)

    const {id}=useParams()

    
    useEffect(() => {
        getCurrentScene()
     }, []);

     useEffect(() => {
        getDevicesToAdd()
        getSceneDevices()
     }, [sceneReady]);


     useEffect(() => {
        //generate panels

     }, [devicesReady]);

    const getCurrentScene=()=>{
        axios.get(scene_url+'/'+id,{})
        .then((response) => response.data)
        .then((data) => {
            setScene(data)
            setSceneReady(true)
        })
    }

    const getSceneDevices=()=>{
        axios.get(scene_url+'/devices/'+id,{}).then((response) => response.data)
        .then((data) => {
            setSceneDevices(data)
            setDevicesReady(true)
        })
    }

    const getDevicesToAdd=()=>{
        axios.get(scene_url+'/Add/houseId='+scene?.houseId+'/sceneId='+id, {})
        .then((response) => response.data).
        then((data) => {
            setDevicesToAdd(data)
            setAddReady(true)
        })
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

    const showPanel=(device:Device)=>{
        return(
            <div>
                {devicesReady &&
                    render('Tuya Smart LED',tuyaLEDProps)
                }
            </div>
        );
    }

    const tuyaLEDProps={
        switch_1:"true",
    }

    const tuyaPlugProps={
        switch_led:"true",
        colour_data:"fc51eb",
        intensity:"1000"
    }

    const shellyDefaultProps = {turn:'on',
        white:'50',
        brightness:'50',
        temp:'2500',
        transition:'400' };

 

    const handleAddDeviceToScene =(device:Device)=>{
        axios.get(scene_url+'/Add/deviceId='+device.id+'/sceneId='+id, {}).then((response) => console.log(response.data)).catch(error => {
           console.log(error);
          }); 
    }

    const executeScene=()=>{
        axios.get(scene_url+'/SetScene/'+id+'/'+AuthService.getLoggedUser().id, {}).then((response) => console.log(response.data)).catch(error => {
            console.log(error);
           }); 
    }
    return (
        <div>
        {(sceneReady && addReady && devicesReady) &&
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
              <div> 
                <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Devices to add</Typography>
                </Grid>
                <Grid justifyContent="center" container item xs={12}>
                    <Grid container justifyContent="center" spacing={3}>
                    {devicesToAdd?.map((device)=>
                        <Button onClick={()=>handleAddDeviceToScene(device)}>
                            <Grid item>
                                <Card>
                                    <CardActionArea>
                                        <CardMedia
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
              </div>
            }
            </Grid> 
                   

            <Grid justifyContent="center" container item xs={12}>
                <Button variant="contained" onClick={executeScene}>Execute Scene</Button>           
            </Grid>   

        </Grid>
    }
    </div>
    
      );
}