import { Box, Button, ButtonProps, Card, Grid, Paper, Slider, styled, Switch, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CodeValue, ComponentProp, DeviceDto, UserPermisson } from '../../../interfaces';
import TuyaLEDService from '../../../Services/TuyaLEDService';
import DeviceService from '../../../Services/DeviceService';
import { SketchPicker } from 'react-color';
import SceneService from '../../../Services/SceneService';
import DeleteComponent from '../../delete/DeleteComponent';


const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));

const styles = {
  dupa: {
    marginLeft: '0px'
  },
};

export default function TuyaLED(props?:ComponentProp) {

  const { deviceId } = useParams();
  const [device,setDevice]=useState<DeviceDto>();
  const [deviceFetched,setDeviceFetched]=useState<boolean>(false);
  const [sketchColor, setSketchColor] = useState("#0000FF");
  const label = { inputProps: { 'aria-label': 'Size switch demo'}};
  const [checked, setChecked] = useState<boolean>(true);
  const [intensity, setIntensity] = React.useState<number>(200);
  const [isSceneComponent, setIsSceneComponent]=useState<boolean>(false);

  const [devicePermission, setDevicePermisson] = useState<UserPermisson>();
  const [controlEnabled, setControlEnabled] = useState<boolean>(false);
  const [confirmDelete, setConfirmDelete] = useState<boolean>(false);
  const [deleteMessage, setDeleteMessage] = useState<string>('');
  const [openDeleteModal, setOpenDeleteModal] = useState<boolean>(false);

  let first:boolean=true;

  useEffect(() => { 
    getDeviceById();
    getDevicePermisson()
  }, []);

  useEffect(() => {  
    if(device!==undefined && !isSceneComponent){
      getDeviceStatus(device?.id);
    }     
    else if(props!==undefined  && props.device!==undefined)
      setDefaults(props?.device?.props)
 
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

  const setDefaults=(status: CodeValue[])=>
  {
    status.forEach(prop=>{
      switch(prop.code){
        case "colour_data":
          let value = prop.value;
          if(value.at(0)!='#')
            value='#'+value
          setSketchColor(value)
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

   const saveBrightness=()=>{
    if(props!=undefined && props.sceneId!=undefined && props.device!=undefined){
      const newProps=[new CodeValue("intensity", String(intensity)),]
      let newDevice = props.device;
      newDevice.props=newProps;
      SceneService.putNewSceneProps(props?.sceneId,newDevice)
    } 
   }
   const saveColor=()=>{
    if(props!=undefined && props.sceneId!=undefined && props.device!=undefined){
      const newProps=[new CodeValue("colour_data",sketchColor.slice(1))]
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

  const deleteDeviceFromScene=()=>{
    setOpenDeleteModal(true);
    setDeleteMessage('Do you really want to delete device from scene?');
  }

  const deleteDeviceFromHouse=()=>{
    setOpenDeleteModal(true);
    setDeleteMessage('Do you really want to delete device from house?');
  }

  useEffect(() => {  
    if(confirmDelete===true){
      if(device!==undefined && !isSceneComponent){
        console.log('device has been deleted from house')
      }     
      else if(props!==undefined  && props.device!==undefined)
        console.log('device has been deleted from scene')
    }
    setConfirmDelete(false);
  }, [confirmDelete]);
  
  return (
    <Grid justifyContent="center" container item >
      <Box sx={{ width: 800 }}>        
          <Grid container spacing={3}>

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
                {!isSceneComponent && <ColorButton  disabled= {!controlEnabled} onClick = {changeColor} variant="contained">Change color</ColorButton>}
                {isSceneComponent && <ColorButton disabled= {!controlEnabled} onClick = {saveColor} variant="contained">Save color</ColorButton>}
                </Grid>
        
              </Grid>
                <Grid justifyContent="center" container item>
                  <Switch  disabled= {!controlEnabled} checked={checked} onChange={handleSwitchChange} {...label} />
                </Grid>
                <Grid justifyContent="center" container item  spacing={2}>
                <Box sx={{ width: 600 }}>
                    <Slider 
                      sx={styles.dupa}
                      disabled= {!controlEnabled} 
                      aria-label="Volume" 
                      value={intensity} 
                      onChange={handleIntensityChange} 
                      color="primary" 
                      min={0} max={1000} 
                      defaultValue={1000}/> 
                </Box>    
                <Grid container justifyContent="center" rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
                  <Grid container justifyContent="center" item xs={12}>
                    {!isSceneComponent && <Button  disabled= {!controlEnabled} variant="contained" onClick = {changeIntensity}>Change brightness</Button>}
                    {isSceneComponent  && <Button  disabled= {!controlEnabled} variant="contained" onClick = {saveBrightness}>Save brightness</Button>}
                  </Grid>

                </Grid>                                     
              </Grid>
          </Grid>  
          <DeleteComponent isSceneComponent={isSceneComponent} device={device} sceneId={props?.sceneId}/>
      </Box>
    </Grid>
    );
}