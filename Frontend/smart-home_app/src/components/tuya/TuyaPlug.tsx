import { Box, Card, Grid, LinearProgress, Switch, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CodeValue, ComponentProp, DeviceDto } from '../../interfaces';
import DeviceService from '../../Services/DeviceService';
import SceneService from '../../Services/SceneService';
import TuyaPlugService from '../../Services/TuyaPlugService';

export default function TuyaPlug(props?:ComponentProp) {

  const { deviceId } = useParams();
  const [device,setDevice]=useState<DeviceDto>()
  const [isSceneComponent, setIsSceneComponent]=useState<Boolean>(false);
  const [deviceFetched,setDeviceFetched]=useState<boolean>(false);
  const [loading, setLoading]=useState<boolean>(false);
  const label = { inputProps: { 'aria-label': 'Size switch demo' } };
  const [checked, setChecked] = React.useState(true);

  let first:boolean=true;

  useEffect(() => {
     getDeviceById()
  }, []);

  useEffect(() => {  
    if(device!==undefined && !isSceneComponent){
      getDeviceStatus(device?.id);
    }     
    else if(props!==undefined  && props.device!==undefined){
      setDefaults(props?.device?.props)
      setLoading(false);
    } 
  }, [deviceFetched]);

  const getDeviceStatus= async (deviceId:number)=>{
    let response = await TuyaPlugService.getDeviceStatus(deviceId);
    if(response!==undefined)
      setDefaults(response)
  }

  const setDefaults=(status: CodeValue[])=>
  {
    status.forEach(prop=>{
      switch(prop.code){
        case "switch_1":
          setChecked(Boolean(prop.value))   
          if(isSceneComponent){
            if(prop.value=='false'){
              setChecked(false)
            }
          } 
          break;      
      }
    });
  }

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

  const saveScene=(value: boolean)=>{
    if(props!=undefined && props.sceneId!=undefined && props.device!=undefined){
      const newProps=[
        new CodeValue("switch_1",String(value))
      ]
      let newDevice = props.device;
      newDevice.props=newProps;
      SceneService.putNewSceneProps(props?.sceneId,newDevice)
    }     
   }

   const handleSwitchChange =()=>{
    setChecked(!checked)
    if(device!==undefined){
      if(first){
        if(isSceneComponent){
          saveScene(!checked)
        }
        else{
          TuyaPlugService.switchPlug(device.id,!checked)          
        }
        first=false;
      }
      else{
        if(isSceneComponent){
          saveScene(checked)
        }
        else{
          TuyaPlugService.switchPlug(device.id,checked)          
        }
      }
    }
   }

    return (
        <Card>
          {(deviceFetched && !loading) 
            ?
            <Grid container spacing={3}>
                <Grid justifyContent="center" container item xs={12}>
                      <Typography  variant="h4" >Device name: {device?.name} </Typography>
                </Grid>
                <Grid item justifyContent="center" container >
                  <Grid item ><img width="300" height="300" src={device?.imagePath}/></Grid>
                </Grid>
                <Grid item xs={12} md={8} lg={25}>
                <Switch checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />
              
                </Grid>
            </Grid>
            :
          <Box sx={{ width: '100%' }}>
            <LinearProgress />
          </Box>
          }
        </Card>
      );
}