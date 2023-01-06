import { Button, Card, CardActionArea, CardContent, CardMedia, Grid, Paper, Slider, Switch, Typography } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Device, Instruction, Scene } from '../interfaces';
import { device_url, instruction_url, scene_url } from '../urls';
import ShellyDuo from './shelly/ShellyDuo';
import TuyaLED from './tuya/TuyaLED';
import TuyaPlug from './tuya/TuyaPlug';


export default function SceneComponent() {

    const [scene, setScene]=useState<Scene>()
    const [devices,setDevices]=useState<Array<Device>>()
    const [allDevicesm, setAllDevices]=useState<Array<Device>>()
    const [instructions, setInstructions]=useState<Array<Instruction>>();
    const [devicesToAdd, setDevicesToAdd]=useState<Array<Device>>()


    //make array of devices in scene and setup instructions
    //get all devices of that user
    //filter devices that are in the scene
    //make add option

    //while adding add current status of devices as default 

    const {id}=useParams()

    const getInstructions=()=>{

        axios.get(instruction_url+'sceneId='+id,{})
        .then((response) => response.data)
        .then((data) => {setInstructions(data)})
        .catch(error => {
          console.log(error)
        });
    }

    const getCurrentScene=()=>{
        axios.get(scene_url+id,{})
        .then((response) => response.data)
        .then((data) => {setScene(data)})
        .catch(error => {
          console.log(error)
        });
    }

    const getDevices=()=>{
        axios.get(scene_url+'/devices/'+id,{})
        .then((response) => response.data)
        .then((data) => {setDevices(data)})
        .catch(error => {
          console.log(error)
        });
    }
    const getAllDevices=()=>{
        axios.get(device_url+'/houseId='+scene?.houseId, {})
        .then((response) => response.data)
        .then((data) => {
            setAllDevices(data)
            setDevicesToAdd(allDevicesm?.filter(el=>!devices?.includes(el)))
        })
        .catch(error => {
          console.log(error)
        });
    }

    useEffect(() => {
       getCurrentScene()
       getInstructions()
       getDevices()    
       getAllDevices()

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

    const handleDeviceClicked =(devices:Device)=>{

    }

    return (
        <Grid container spacing={3}>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h4" >Welcome to scene: {scene?.name}</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Your current devices</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>
                <Grid container justifyContent="center" spacing={3}>
                {devices?.map((device)=>
                    <Button onClick={()=>handleDeviceClicked(device)}>
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