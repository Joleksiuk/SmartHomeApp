import { Button, Grid, Paper, Slider, Switch, TextField, Typography } from '@mui/material';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CodeValue, ComponentProp, Device } from '../../interfaces';
import { Component } from '../../Services/ComponentService';
import TuyaLEDService from '../../Services/TuyaLEDService';
import { device_url } from '../../urls';



export default function TuyaLED(props?:ComponentProp) {

  const { deviceId } = useParams();
  const [device,setDevice]=useState<Device>();
  const [colourData, setColourData] = useState<any>("")

  const readProps=()=>{
    let value = props?.pp?.find(elem => elem.code == 'switch_led')?.value;

    let valueMapped: boolean = false;
    if (value == 'false'){
      valueMapped = false;
    }
    else if (value == 'true'){ 
      valueMapped = true;
    }
    setChecked(valueMapped);
    setColourData(props?.pp?.find(elem => elem.code == 'colour_data')?.value)
    setIntensity(Number(props?.pp?.find(elem => elem.code == 'intensity')?.value))
  }

  useEffect(() => {

    readProps()
    getDeviceById();
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
      console.log(TuyaLEDService.getDeviceDetails(device.specificId))
  }
   const changeIntensity=()=>{
    if(device!==undefined)
      TuyaLEDService.changeIntensity(device.specificId,intensity.toString())
   }

   const changeColor=()=>{
      if(device!==undefined)
        TuyaLEDService.changeColor(device.specificId,colorHex)
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
    if(device!==undefined)
      TuyaLEDService.switchLed(device.specificId,checked)
   }

  const handleIntensityChange = (event: Event, newValue: number | number[]) => {
    setIntensity(newValue as number);
  };

  const label = { inputProps: { 'aria-label': 'Size switch demo' } };
  const [checked, setChecked] = React.useState(true);
  const [intensity, setIntensity] = React.useState<number>(200);
  const [colorHex, setColorValue] = useState<string>("");

  const onTextChange = (e: any) => setColorValue(e.target.value);
    return (

        <Grid container spacing={3}>

            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h4" >Device name: {device?.name} </Typography>
            </Grid>
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
                max={1000}
                defaultValue={1000}
                />
            <Button onClick = {changeIntensity}>Change brightness</Button>

            <TextField  onChange={onTextChange} defaultValue={colourData} value={colorHex} label={colourData}/>
            <Button onClick = {changeColor}>Change color</Button>
        
            </Grid>
        </Grid>

      );
}