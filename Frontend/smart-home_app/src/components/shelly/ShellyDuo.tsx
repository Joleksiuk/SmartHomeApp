import { Button, Grid, Paper, Slider, Switch, Typography } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Device } from '../../interfaces';
import ComponentService, { Component } from '../../Services/ComponentService';
import ShellyDuoService, { State } from '../../Services/ShellyDuoService';
import { device_url } from '../../urls';

export interface ShellyDuoProps{
    turn?:string,
    white?:string,
    brightness?:string,
    temp?:string,
    transition?:string
}

export default function ShellyDuo(props?:ShellyDuoProps) {

  const { deviceId } = useParams();
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

  const [component, setComponent]=useState<Component>()
   const getDeviceStatus=()=>{
    if(device!==undefined)
    console.log(ShellyDuoService.getDeviceStatus(device?.specificId))
  }
   const changeBrightness=()=>{
    if(device!==undefined)
      ShellyDuoService.changeBrightness(device?.specificId,brigthnessValue.toString())
   }
   const changeTemp=()=>{
    if(device!==undefined)
      ShellyDuoService.changeTemperature(device?.specificId,tempValue.toString())
   }
   const changeWhite=()=>{
    if(device!==undefined)
    ShellyDuoService.changeWhiteness(device?.specificId,whiteValue.toString())
   }

   const getComponent=()=>{

    const component_url = "http://localhost:8080/component";
    axios.get(component_url+'/name=Shelly Duo', {})
        .then((response) => response.data)
        .then((data) => setComponent(data))
        .catch(error => {
        console.log(error)
        });
   }

   const handleSwitchChange =()=>{
      setChecked(!checked)
      let state =  State.Off
      if(checked){
        state = State.On;
      }
      if(deviceId!==undefined)
      ShellyDuoService.switchBulb(deviceId, state)
   }


  const handleBrightnessChange = (event: Event, newValue: number | number[]) => {
    setBrightnessValue(newValue as number);
  };

  const handleTempChange = (event: Event, newValue: number | number[]) => {
    setTempValue(newValue as number);
  };

  const handleWhiteChange = (event: Event, newValue: number | number[]) => {
    setWhiteValue(newValue as number);
  };

  const label = { inputProps: { 'aria-label': 'Size switch demo' } };
  const [checked, setChecked] = React.useState(true);
  const [brigthnessValue, setBrightnessValue] = React.useState<number>(1);
  const [tempValue, setTempValue] = React.useState<number>(1);
  const [whiteValue, setWhiteValue] = React.useState<number>(1);

  const gradient = 'linear-gradient(to right, #e0561f,#ffd54d,#dcf7f7, #7cc1c2, #09c0e0)';

    return (
      <Paper
        sx={{
        p: 2,
        display: 'flex',
        flexDirection: 'column', 
      }}>
          <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h4" >Device name: {device?.name} </Typography>
          </Grid>
          <Grid container spacing={3}>

              <Grid item xs={12} md={8} lg={25}>
              <img src={component?.imagePath}/>
              <Button onClick = {getDeviceStatus}>Get device status</Button>
              <Button onClick = {getComponent}>Get component</Button>
              <Switch checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />
              
              <Slider aria-label="Volume" value={brigthnessValue} onChange={handleBrightnessChange} color="primary"/>
              <Button onClick = {changeBrightness}>Change brightness</Button>
              
              <Slider 
                aria-label="Volume" 
                value={tempValue} 
                onChange={handleTempChange} 
                style={{ background: gradient }} 
                color="secondary"
                min={2700}
                max={6500}/>
              <Button onClick = {changeTemp}>Change temperature</Button>
              
              <Slider aria-label="Volume" value={whiteValue} onChange={handleWhiteChange} color="primary"/>
              <Button onClick = {changeWhite}>Change White</Button>

              </Grid>
          </Grid>
        </Paper>
      );
}