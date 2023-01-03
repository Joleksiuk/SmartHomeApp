import { Button, Grid, Paper, Slider, Switch, TextField } from '@mui/material';
import axios from 'axios';
import React, { useState } from 'react';
import { Component } from '../../Services/ComponentService';
import TuyaLEDService from '../../Services/TuyaLEDService';

export default function TuyaLED() {

  const deviceId = 'bf2b8148e20535ca2eaik5'
  const [component, setComponent]=useState<Component>()

   const getDeviceStatus=()=>{
    console.log(TuyaLEDService.getDeviceDetails(deviceId))
  }
   const changeIntensity=()=>{
      TuyaLEDService.changeIntensity(deviceId,intensity.toString())
   }

   const changeColor=()=>{
        console.log(colorHex)
       TuyaLEDService.changeColor(deviceId,colorHex)
   }

   const randomColors:string[] = ['#e74c3c','#3498db','#9b59b6','#e67e22','#f1c40f','#1abc9c','#2ecc71','#e74c3c','#3498db','#9b59b6','#e67e22','#f1c40f','#1abc9c','#2ecc71','#e74c3c']

   const colorCircles=()=>{
   }

   const getComponent=()=>{
    const component_url = "http://localhost:8080/component";
    axios.get(component_url+'/name=LED', {})
        .then((response) => response.data)
        .then((data) => setComponent(data))
        .catch(error => {
        console.log(error)
        });
   }

   const handleSwitchChange =()=>{
    setChecked(!checked)
    TuyaLEDService.switchLed(deviceId,checked)
   }

  const handleIntensityChange = (event: Event, newValue: number | number[]) => {
    setIntensity(newValue as number);
  };

  const label = { inputProps: { 'aria-label': 'Size switch demo' } };
  const [checked, setChecked] = React.useState(true);
  const [intensity, setIntensity] = React.useState<number>(1);
  const [colorHex, setTextValue] = useState<string>("");

  const onTextChange = (e: any) => setTextValue(e.target.value);
    return (

        <Grid container spacing={3}>

            <Grid item xs={12} md={8} lg={25}>
            <img src={component?.imagePath}/>
            <Button onClick = {getDeviceStatus}>Get device status</Button>
            <Button onClick = {getComponent}>Get component</Button>
            <Switch checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />
            
            <Slider 
                aria-label="Volume" 
                value={intensity} 
                onChange={handleIntensityChange} 
                color="primary" 
                min={0}
                max={1000}/>
            <Button onClick = {changeIntensity}>Change brightness</Button>

            <TextField  onChange={onTextChange} value={colorHex} label={"hex color"}/>
            <Button onClick = {changeColor}>Change color</Button>
        
            </Grid>
        </Grid>

      );
}