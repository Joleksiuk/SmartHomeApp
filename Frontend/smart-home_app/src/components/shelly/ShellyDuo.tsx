import { Button, Grid, Paper, Slider, Switch } from '@mui/material';
import axios from 'axios';
import React, { useState } from 'react';
import ComponentService, { Component } from '../../Services/ComponentService';
import ShellyDuoService, { State } from '../../Services/ShellyDuoService';

export interface ShellyDetails{
    state:State,
    brightness:string,
    temp:string,
    white:string
}

export default function ShellyDuo() {

  const deviceId = 'e8db84d500b1'
  const [component, setComponent]=useState<Component>()
   const getDeviceStatus=()=>{
    console.log(ShellyDuoService.getDeviceStatus(deviceId))
  }
   const changeBrightness=()=>{
      ShellyDuoService.changeBrightness(deviceId,brigthnessValue.toString())
   }
   const changeTemp=()=>{
      ShellyDuoService.changeTemperature(deviceId,tempValue.toString())
   }
   const changeWhite=()=>{
    ShellyDuoService.changeWhiteness(deviceId,whiteValue.toString())
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