import { Button, ButtonProps, Card, Grid, Slider, styled, Switch, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CodeValue, ComponentProp, DeviceDto } from '../../interfaces';
import TuyaLEDService from '../../Services/TuyaLEDService';
import DeviceService from '../../Services/DeviceService';
import { SketchPicker } from 'react-color';
import SceneService from '../../Services/SceneService';

export default function TuyaLED(props?:ComponentProp) {

  const { deviceId } = useParams();
  const [device,setDevice]=useState<DeviceDto>();
  const [deviceFetched,setDeviceFetched]=useState<boolean>(false);
  const [sketchColor, setSketchColor] = useState("#0000FF");
  const label = { inputProps: { 'aria-label': 'Size switch demo'}};
  const [checked, setChecked] = useState<boolean>(true);
  const [intensity, setIntensity] = React.useState<number>(200);
  const [isSceneComponent, setIsSceneComponent]=useState<Boolean>(false);

  let first:boolean=true;

  useEffect(() => { 
    getDeviceById();
  }, []);

  useEffect(() => {  
    if(device!==undefined && !isSceneComponent){
      getDeviceStatus(device?.id);
    }     
    else if(props!==undefined  && props.device!==undefined)
      setDefaults(props?.device?.props)
 
  }, [deviceFetched]);


  const getDeviceById = async ()=>{
    if(deviceId!==undefined){
      let response = await DeviceService.getDeviceDto(Number(deviceId))
      setDevice(response)
    }   
    else if(props!==undefined && props.device!==undefined){
      setDevice(props.device)
      setIsSceneComponent(true)
      setDefaults(props.device?.props)
    }
    setDeviceFetched(true)
  }

  const setDefaults=(status: CodeValue[])=>
  {
    status.forEach(prop=>{
      switch(prop.code){
        case "colour_data":
          setSketchColor(prop.value)
          break;
        case "switch_led":
          setChecked(Boolean(prop.value))   
          if(isSceneComponent){
            if(prop.value=='false'){
              setChecked(false)
            }
          } 
          break;
        case "intensity":
          setIntensity(Number(prop.value));
          break;       
      }
    });
  }

   const getDeviceStatus= async (deviceId:number)=>{
      let response = await TuyaLEDService.getDeviceStatus(deviceId);
      if(response!==undefined)
        setDefaults(response)
  }

   const changeIntensity=()=>{
    if(device!==undefined)
      TuyaLEDService.changeIntensity(device.id,intensity.toString())
   }

   const changeColor=()=>{
      if(device!==undefined)
        TuyaLEDService.changeColor(device.id,sketchColor)
   }

   const handleSwitchChange =()=>{
    setChecked(!checked)
    if(device!==undefined){
      if(first){
        TuyaLEDService.switchLed(device.id,!checked)
        first=false;
      }
      else{
        TuyaLEDService.switchLed(device.id,checked)
      }
    }
   }

   const saveScene=()=>{
    if(props!=undefined && props.sceneId!=undefined && props.device!=undefined){
      const newProps=[
        new CodeValue("switch_led",String(checked)),
        new CodeValue("intensity", String(intensity)),
        new CodeValue("colour_data",sketchColor.slice(1))
      ]
      let newDevice = props.device;
      newDevice.props=newProps;
      SceneService.putNewSceneProps(props?.sceneId,newDevice)
    }     
   }

  const handleIntensityChange = (event: Event, newValue: number | number[]) => {
    setIntensity(newValue as number);
  };
  
  const ColorButton = styled(Button)<ButtonProps>(({ theme }) => ({
    color: theme.palette.getContrastText(sketchColor),
    backgroundColor: sketchColor,
    '&:hover': {
      backgroundColor: "#474798",
    },
  }));
  
  return (
      <Card>
      <Grid container>

          <Grid justifyContent="center" container item >
              <Typography  variant="h4" >Device name: {device?.name} </Typography>
          </Grid>
          <Grid justifyContent="center" container item>
            
            <Grid item justifyContent="center" container >
            <Grid item ><img width="300" height="300" src={device?.imagePath}/></Grid>
              <SketchPicker              
              color={sketchColor}
              onChange={(e) => setSketchColor(e.hex)}
            />
             {!isSceneComponent && <ColorButton onClick = {changeColor} variant="contained">Change color</ColorButton>}
            </Grid>
    
          </Grid>
          <Grid justifyContent="center" container item>
            <Switch checked={checked} onChange={handleSwitchChange} {...label} />
          </Grid>
          <Grid justifyContent="center" container item >
            <Slider 
                aria-label="Volume" 
                value={intensity} 
                onChange={handleIntensityChange} 
                color="primary" 
                min={0}
                max={1000}
                defaultValue={1000}
                />                            
            {!isSceneComponent && <Button variant="contained" onClick = {changeIntensity}>Change brightness</Button>}
            {isSceneComponent  && <Button variant="contained" onClick = {saveScene}>Save Scene</Button>}
          </Grid>
      </Grid>
      </Card>
    );
}