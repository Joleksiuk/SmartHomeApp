import { Box, Card, Grid, LinearProgress, Paper, Switch, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CodeValue, ComponentProp, DeviceDto, UserPermisson } from '../../../interfaces';
import DeviceService from '../../../Services/DeviceService';
import SceneService from '../../../Services/SceneService';
import TuyaPlugService from '../../../Services/TuyaPlugService';
import DeleteComponent from '../../delete/DeleteComponent';
import { DeviceImage, MaterialUISwitch, PaperStyle, switchShelly } from '../../StyleElements';

export default function TuyaPlug(props?:ComponentProp) {

  const { deviceId } = useParams();
  const [device,setDevice]=useState<DeviceDto>()
  const [isSceneComponent, setIsSceneComponent]=useState<boolean>(false);
  const [deviceFetched,setDeviceFetched]=useState<boolean>(false);
  const [loading, setLoading]=useState<boolean>(false);
  const label = { inputProps: { 'aria-label': 'Size switch demo' } };
  const [checked, setChecked] = React.useState(true);
  const [devicePermission, setDevicePermisson] = useState<UserPermisson>();
  const [controlEnabled, setControlEnabled] = useState<boolean>(false);

  let first:boolean=true;

  useEffect(() => {
     getDeviceById();
     getDevicePermisson();
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

  useEffect(() => {  
    if(devicePermission!=undefined && devicePermission.canControl!=undefined ){
      setControlEnabled(devicePermission?.canControl)
    }
 
  }, [devicePermission]);

  const getDevicePermisson=async()=>{

    if(deviceId!=undefined){
      let response = await DeviceService.getDevicePermission(deviceId);
      setDevicePermisson(response)
      console.log(response)

    }
    else if(props?.device?.id!=undefined){
      let response = await DeviceService.getDevicePermission(props?.device?.id.toString());
      setDevicePermisson(response)
      console.log(response)

    }
    if(devicePermission!=undefined && devicePermission.canControl!=undefined ){
      setControlEnabled(devicePermission?.canControl)
    }
  }

  const getDeviceStatus= async (deviceId:number)=>{
    let response = await TuyaPlugService.getDeviceStatus(deviceId);
    if(response!==undefined)
      setDefaults(response)
  }

  const setDefaults=(status: CodeValue[])=>
  {
    if(status===undefined || status.length===0){
      return;
    }
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
        <Box sx={{ width: 800 }}>    
        <Paper sx={PaperStyle}>
          {(deviceFetched && !loading) 
            ?
            <Grid container spacing={3}>
                <Grid justifyContent="center" container item xs={12}>
                      <Typography  variant="h4" >Device name: {device?.name} </Typography>
                </Grid>
                <Grid item justifyContent="center" container >
                  <Grid item ><img  style ={DeviceImage} width="150" height="150" src={device?.imagePath}/></Grid>
                  <MaterialUISwitch sx={switchShelly} disabled= {!controlEnabled}  checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />
                </Grid>
                <Grid item xs={12} md={8} lg={25}>
               
              
                </Grid>
                <Grid justifyContent="center" container item xs={12}>
                  <br></br>
                  <DeleteComponent isSceneComponent={isSceneComponent} device={device} sceneId={props?.sceneId}/>
                </Grid>
            </Grid>
            :
          <Box sx={{ width: '100%' }}>
            <LinearProgress />
          </Box>
          }
         </Paper>
        </Box>
      );
}