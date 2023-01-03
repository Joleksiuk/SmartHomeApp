import { Button, Grid, Paper, Slider, Switch } from '@mui/material';
import React from 'react';
import ShellyDuoService, { State } from '../Services/ShellyDuoService';

export default function TestComponent() {

  const deviceId = 'e8db84d500b1'
   const test1=()=>{
    ShellyDuoService.getDeviceStatus(deviceId);
  }
   const changeBrightness=()=>{
      ShellyDuoService.changeBrightness(deviceId,sliderValue.toString())
   }

   const handleSwitchChange =()=>{
      setChecked(!checked)
      let state =  State.Off
      if(checked){
        state = State.On;
      }
      ShellyDuoService.switchBulb(deviceId, state)
   }

  const handleSliderChange = (event: Event, newValue: number | number[]) => {
    setSliderValue(newValue as number);
  };

  const label = { inputProps: { 'aria-label': 'Size switch demo' } };
  const [checked, setChecked] = React.useState(true);
  const [sliderValue, setSliderValue] = React.useState<number>(1);

    return (
      <Paper
        sx={{
        p: 2,
        display: 'flex',
        flexDirection: 'column', 
      }}>
          <Grid container spacing={3}>
              <Grid item xs={12} md={8} lg={25}>
              <Button onClick = {test1}>test1</Button>
              <Button onClick = {changeBrightness}>Test2</Button>
              <Switch checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />             
              <Slider aria-label="Volume" value={sliderValue} onChange={handleSliderChange} color="primary"/>
              <Button onClick = {changeBrightness}>Change brightness</Button>        
              </Grid>
          </Grid>
        </Paper>
      );
}