
import { useEffect, useState } from 'react'
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Alert, Box, Button, CardActionArea, Grid, LinearProgress, Stack, TextField } from '@mui/material'; 
import axios from 'axios';
import { Component, Device, House } from '../interfaces';
import { device_url } from '../urls';
import TuyaService from '../Services/TuyaService';
import ShellyService from '../Services/ShellyService';


export interface AddNewDeviceProps{
    components?:Component[],
    deviceAdded?: boolean,
    home?: House
}

export default function AddNewDevice( props?:AddNewDeviceProps) {

    const [components, setComponents]=useState<Array<Component>>([])
    const [newDevice, setNewDevice]=useState<Device>();
    const [showForm,setShowForm]=useState<Boolean>(false); 
    const [showError, setShowError] = useState<Boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>("");
    const [loading, setLoading]=useState<boolean>(true);
    const [currentComponent, setCurrentComponent]=useState<Component>();

    useEffect(() => {  
        if(props!=undefined && props.components!=undefined){
            setComponents(props?.components)
            setLoading(false);
        }    
    }, []);

    const handleNewComponentClicked=(component:Component)=>{
        let device = {
            'id':0,
            'componentId':component.id,
            'houseId': 0,
            'specificId':'',
            'name': component.name
          }
        
        setNewDevice(device)
        setShowForm(true)
        setCurrentComponent(component);
    }

    const handleSubmit=async (event: React.FormEvent<HTMLFormElement>)=>{

        event.preventDefault();
        const data = new FormData(event.currentTarget);
        let name= data.get('name')?.toString();
        let identifier = data.get('identifier')?.toString();
        if(name===""){
            setShowError(true);
            setErrorMessage("Name can't be empty!");
            return;
        } 
        if(identifier===""){
            setShowError(true);
            setErrorMessage("identifier can't be empty!");
            return;
        }
        if(name!==null && name!==undefined && identifier!=null && identifier!==undefined){
            
            switch(currentComponent?.brand){
                case "TUYA":
                    if(await TuyaService.verifyTuyaDeviceId(identifier)==false){     
                        setShowError(true);
                        setErrorMessage("Given device id is incorrect or you don't have permission for this device!");
                        return;
                    }                 
                    break;
                case "Shelly":
                    if( await ShellyService.verifyShellyDeviceId(identifier) ==false){
                        setShowError(true);
                        setErrorMessage("Given device id is incorrect or you don't have permission for this device!");
                        return;
                    }
                    break;
            }
            setLoading(false);
            let device = {
                'componentId': newDevice?.componentId,
                'houseId': props?.home?.id,
                'specificId':identifier,
                'name': name
              }       
            setShowForm(false)  
            axios.post(device_url,device).catch(error => {console.log(error)});             
        }
       
    }

    return (
        <Card>
        {!loading ?
            <Grid container spacing={3}>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6">Add new device: </Typography>
            </Grid> 
            <Grid justifyContent="center" container item xs={12}>
                            
                <Grid container justifyContent="center" spacing={3}>
                {components?.map((component)=>
                    <Button onClick={() =>  handleNewComponentClicked(component) }>
                    <Grid item>
                        <Card>
                            <CardActionArea>
                                <CardMedia
                                component="img"
                                height="150"
                                image={component.imagePath }
                                />
                                <CardContent>
                                <Typography gutterBottom variant="h5" component="div">
                                    {component.name } 
                                </Typography>
                                </CardContent>
                            </CardActionArea>
                        </Card>
                    </Grid>
                    </Button>
                )}
                </Grid>
            </Grid> 
            <Grid justifyContent="center" container item xs={12}>
            {showForm &&
                
                <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>  
                    {showError &&
                        <Grid item xs={12}>
                            <Stack sx={{ width: '100%' }} spacing={2}>
                            <Alert severity="error">{errorMessage}</Alert>
                            </Stack>
                        </Grid>
                    }  
                     <Typography  variant="h6">{newDevice?.name}</Typography>

                    <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="name"
                    label="name"
                    name="name"
                    autoComplete="name"
                    autoFocus
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="identifier"
                        label="identifier"
                        name="identifier"
                        autoComplete="identifier"
                        autoFocus
                    />   
                    <Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
                        Submit
                    </Button>
                </Box>
            }
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
