import { Button, Grid, Paper, Slider, Switch } from '@mui/material';
import axios from 'axios';
import React, { useState } from 'react';
import { Component } from '../../Services/ComponentService';
import TuyaPlugService from '../../Services/TuyaPlugService';

export default function ShellyDuo() {

  const deviceId = 'bf23844d1e909d1f8bah2r'
  const [component, setComponent]=useState<Component>()

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
      TuyaPlugService.switchPlug(deviceId, checked)
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

              <Grid item xs={12} md={8} lg={25}>
              <img src={component?.imagePath}/>
              <Button onClick = {getComponent}>Get component</Button>
              <Switch checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />
            
              </Grid>
          </Grid>
        </Paper>
      );
}