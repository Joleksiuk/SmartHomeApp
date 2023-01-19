import { Box, Button, Grid, LinearProgress, Paper, Slider, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CodeValue, ComponentProp, DeviceDto, UserPermisson } from '../../../interfaces';
import DeviceService from '../../../Services/DeviceService';
import SceneService from '../../../Services/SceneService';
import ShellyDuoService, { State } from '../../../Services/ShellyDuoService';
import DeleteComponent from '../../delete/DeleteComponent';
import { ButtonShelly, DeviceImage, MaterialUISwitch, PaperStyle, SliderShelly, switchShelly } from '../../StyleElements';

export default function ShellyDuo(props?:ComponentProp) {

  const { deviceId } = useParams();
  const [device,setDevice]=useState<DeviceDto>()
  const [deviceFetched,setDeviceFetched]=useState<boolean>(false);
  const [loading, setLoading]=useState<boolean>(true);

  const [isSceneComponent, setIsSceneComponent]=useState<boolean>(false); 
  const [controlEnabled, setControlEnabled] = useState<boolean>(false);
  const [devicePermission, setDevicePermisson] = useState<UserPermisson>();

  let first:boolean=true;

   useEffect(() => { 
    getDeviceById();
    getDevicePermisson();
  }, []);

  useEffect(() => {  
    if(devicePermission!=undefined && devicePermission.canControl!=undefined ){
      setControlEnabled(devicePermission?.canControl)
    }
 
  }, [devicePermission]);

  useEffect(() => {  
    if(device!==undefined && !isSceneComponent){
      getDeviceStatus(device?.id);
    }     
    else if(props!==undefined  && props.device!==undefined){
      setDefaults(props?.device?.props)
    }
    setLoading(false);

  }, [deviceFetched]);

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
  }

  const getDeviceById = async ()=>{
    if(deviceId!==undefined){
      let response = await DeviceService.getDeviceDto(Number(deviceId))
      setDevice(response)
      setDeviceFetched(true)
    }   
    else if(props!==undefined && props.device!==undefined){

      setDevice(props.device)
      setIsSceneComponent(true)
      setDeviceFetched(true)
    }
    
  }
  
  const setDefaults=(status: CodeValue[])=>
  {
    status.forEach(prop=>{
      switch(prop.code){
        case "temp":
          setTempValue(Number(prop.value))
          break;
        case "switch":
          if(prop.value==='on'){
            setChecked(true)
          }
          else{
            setChecked(false)
          }
          break;
        case "brightness":
          setBrightnessValue(Number(prop.value));
          break;    
        case "white":
          setWhiteValue(Number(prop.value));
          break;    
      }
    });
  }

  const getDeviceStatus= async (deviceId:number)=>{
    let response = await ShellyDuoService.getDeviceStatus(deviceId);
    if(response!==undefined)
      setDefaults(response)
}

   const changeBrightness=()=>{
    if(deviceId!==undefined)
      ShellyDuoService.changeBrightness(deviceId,brigthnessValue.toString())
   }
   const changeTemp=()=>{
    if(deviceId!==undefined)
      ShellyDuoService.changeTemperature(deviceId,tempValue.toString())
   }
   const changeWhite=()=>{
    if(deviceId!==undefined)
    ShellyDuoService.changeWhiteness(deviceId,whiteValue.toString())
   }

   const handleSwitchChange =()=>{
      setChecked(!checked)
      let state =  State.Off

      if(first){
        if(checked){
          state = State.Off;
        }
        else{
          state = State.On;
        }
      }
      else{

        if(checked){
          state = State.On;
        }
        else{
          state = State.Off;
        }
      }

      if(deviceId!==undefined){
        ShellyDuoService.switchBulb(deviceId, state)       
      }
   }

   const saveScene=()=>{
    let state =  State.Off
    if(checked){
      state = State.On;
    }
    if(props!=undefined && props.sceneId!=undefined && props.device!=undefined){
      const newProps=[
        new CodeValue("switch",state),
        new CodeValue("brightness",brigthnessValue.toString()),
        new CodeValue("temp",tempValue.toString()),
      ]
      let newDevice = props.device;
      newDevice.props=newProps;
      SceneService.putNewSceneProps(props?.sceneId,newDevice)
    }     
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
      <Grid justifyContent="center" container item xs={12}>
      <Box sx={{ width: 800 }}>  
      <Paper sx={PaperStyle}>
        {(deviceFetched && !loading) ?
        <Grid justifyContent="center" container item xs={12}>
            <Grid justifyContent="center" container item xs={12}>
                  <Typography  variant="h4" >Device name: {device?.name} </Typography>
            </Grid>
            <Grid container spacing={3}>

                <Grid item xs={12} md={8} lg={25}>
                <Grid justifyContent="center" container item xs={12}>
                  <img  style ={DeviceImage} height ="200"src={device?.imagePath}/>
                  <MaterialUISwitch sx={switchShelly} disabled= {!controlEnabled}  checked={checked} onChange={handleSwitchChange} {...label} defaultChecked />
                </Grid>
                <Grid justifyContent="center" container item xs={12}>
                <Slider sx={SliderShelly} aria-label="Volume"  disabled= {!controlEnabled}  value={brigthnessValue} onChange={handleBrightnessChange} color="primary"/>
                {!isSceneComponent && <Button sx={ButtonShelly} disabled= {!controlEnabled}  variant="contained" onClick = {changeBrightness}>Change brightness</Button>}
                <br/>
                <Slider 
                  sx={SliderShelly}
                  disabled= {!controlEnabled} 
                  aria-label="Volume" 
                  value={tempValue} 
                  onChange={handleTempChange} 
                  style={{ background: gradient }} 
                  color="secondary"
                  min={2700}
                  max={6500}/>
                    <br/>
                {!isSceneComponent &&  <Button sx={ButtonShelly} disabled= {!controlEnabled}  variant="contained" onClick = {changeTemp}>Change temperature</Button>}
                {isSceneComponent &&  <Button sx={ButtonShelly} disabled= {!controlEnabled}  variant="contained" onClick = {saveScene}>Save scene</Button>}
                </Grid>
                </Grid>
                <Grid justifyContent="center" container item xs={12}>
                  <DeleteComponent isSceneComponent={isSceneComponent} device={device} sceneId={props?.sceneId}/>
                </Grid>
                </Grid>
          </Grid>
          :
          <Box sx={{ width: '100%' }}>
          <LinearProgress />
          </Box>
        }</Paper>
        </Box>
        </Grid>
      );
}