import { Button, Grid, Paper, Switch, Typography } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Device } from '../../interfaces';
import { Component } from '../../Services/ComponentService';
import TuyaPlugService from '../../Services/TuyaPlugService';
import { device_url } from '../../urls';

export default function ShellyDuo() {

  const { deviceId } = useParams();
  const [component, setComponent]=useState<Component>()
  const [device,setDevice]=useState<Device>()
  
  useEffect(() => {
     getDeviceById()
  }, []);

  const getDeviceById=()=>{
    axios.get(device_url+'/'+deviceId , {})
    .then((response) => response.data)
    .then((data) => {
        setDevice(data)
    })
    .catch(error => {
      console.log(error)
    });
  }

   const getComponent=()=>{

    const component_url = "http://localhost:8080/component";
    axios.get(component_url+'/name=Tuya Smart Plug', {})
        .then((response) => response.data)
        .then((data) => setComponent(data))
        .catch(error => {
        console.log(error)
        });
   }

   const handleSwitchChange =()=>{
      setChecked(!checked)
      if(device!==undefined)
        TuyaPlugService.switchPlug(device.specificId, checked)
   }

  const label = { inputProps: { 'aria-label': 'Size switch demo' } };
  const [checked, setChecked] = React.useState(true);

    return (
      <Paper
        sx={{
        p: 2,
        display: 'flex',
        flexDirection: 'column', 
      }}>
          <Grid container spacing={3}>

              <Grid justifyContent="center" container item xs={12}>
                    <Typography  variant="h4" >Device name: {device?.name} </Typography>
              </Grid>
              <Grid item xs={12} md={8} lg={25}>
              <img src={component?.imagePath}/>
              <Button onClick = {getComponent}>Get component</Button>
              <Switch checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />
            
              </Grid>
          </Grid>
        </Paper>
      );
}