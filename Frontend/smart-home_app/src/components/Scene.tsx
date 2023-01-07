import { Button, Card, CardActionArea, CardContent, CardMedia, Grid, Typography } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CommandDto, Device, Scene } from '../interfaces';
import {scene_url } from '../urls';
import ShellyDuo from './shelly/ShellyDuo';
import TuyaLED from './tuya/TuyaLED';
import TuyaPlug from './tuya/TuyaPlug';

export default function SceneComponent() {

    const [scene, setScene]=useState<Scene>()
    const [sceneDevices,setSceneDevices]=useState<Array<Device>>()
    const [deviceCommandMap,setDeviceCommandMap]=useState<Map<number,Array<CommandDto>>>()
    const [commands, setCommands]=useState<Array<CommandDto>>();
    const [devicesToAdd, setDevicesToAdd]=useState<Array<Device>>()

    const {id}=useParams()

    const getCurrentScene=()=>{
        axios.get(scene_url+'/'+id,{})
        .then((response) => response.data)
        .then((data) => {
            setScene(data)
        })
        .then((data) =>{
            getDevicesToAdd()
            getSceneDevices()
        }
       )
    }

    const getSceneDevices=()=>{
        axios.get(scene_url+'/devices/'+id,{}).then((response) => response.data).then((data) => {setSceneDevices(data)})
    }

    const getDevicesToAdd=()=>{
        axios.get(scene_url+'/Add/houseId='+scene?.houseId+'/sceneId='+id, {}).then((response) => response.data).then((data) => {setDevicesToAdd(data)})
    }

    useEffect(() => {
       getCurrentScene()

    }, []);

    const showPanel=(device:Device)=>{
        return(
            {}
        )
    }

    const componentMap: {[key: string]: React.ComponentType} = {
        'Shelly duo': ShellyDuo,
        'Tuya Smart Plug': TuyaPlug,
        'Tuya Smart LED': TuyaLED,
      };

    const handleAddDeviceToScene =(device:Device)=>{
        axios.post(scene_url+'/Add/deviceId='+device.id+'/sceneId='+id, {}).then((response) => console.log(response.data)).catch(error => {
           console.log(error);
          }); 
    }


    return (
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
        </Grid>
    
      );
}