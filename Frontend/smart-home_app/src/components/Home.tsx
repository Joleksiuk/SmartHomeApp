
import { useEffect, useState } from 'react'
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Alert, Box, Button, CardActionArea, Grid, Stack, TextField } from '@mui/material'; 
import axios from 'axios';
import { Component, Device, House } from '../interfaces';
import { component_url, device_url, house_url } from '../urls';
import { useNavigate, useParams } from 'react-router-dom';

export default function Home() {

    const { id } = useParams();

    const [devices, setDevices]=useState<Array<Device>>([])
    const [components, setComponents]=useState<Array<Component>>([])
    const [home, setHome]=useState<House>();
    const [newDevice, setNewDevice]=useState<Device>();
    const [showForm,setShowForm]=useState<Boolean>(false); 
    const [showError, setShowError] = useState<Boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>("");

    useEffect(() => {
        getCurrentHome()
    
    }, []);

    useEffect(() => {
    
    }, [devices]);

    useEffect(() => {
        getCurrentDevices()
    }, [home]);

    const getAllComponents=()=>{  
        axios.get(component_url+'/all' , {})
        .then((response) => response.data)
        .then((data) => {
            setComponents(data)
        }
            )
        .catch(error => {
          console.log(error)
        });
    }

    const getCurrentDevices=()=>{  
        axios.get(device_url+'/houseId='+home?.id , {})
        .then((response) => response.data)
        .then((data) => {
            setDevices(data)
        }
            )
        .catch(error => {
          console.log(error)
        });
    }

    const getCurrentHome=()=>{
        axios.get(house_url+'/houseId='+id , {})
        .then((response) => response.data)
        .then((data) => {
            setHome(data)
            getAllComponents()        
        }
            )
        .catch(error => {
          console.log(error)
        });
    }

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
    }

    const handleSubmit=(event: React.FormEvent<HTMLFormElement>)=>{

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
           
            let device = {
                'componentId': newDevice?.componentId,
                'houseId': home?.id,
                'specificId':identifier,
                'name': name
              }       
            JSON.stringify(device)
            setShowForm(false)  
            axios.post(device_url,device);                        
        }
    }

    const navigate = useNavigate();
    
    const handleDeviceClicked=(device:Device)=>{

        axios.get(component_url+'/id='+device.componentId , {})
        .then((response) => response.data)
        .then((data) => {
           navigate('/'+data?.name.replace(/\s/g,'')+'/'+device.id)
        }
            )
        .catch(error => {
          console.log(error)
        });
    }


    return (
        <Grid container spacing={3}>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h4" >Welcome to {home?.name}</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Your current devices</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>
                <Grid container justifyContent="center" spacing={3}>
                {devices?.map((device)=>
                    <Button onClick={()=>handleDeviceClicked(device)}>
                        <Grid item>
                            <Card>
                                <CardActionArea>
                                    <CardMedia
                                    component="img"
                                    height="150"
                                    />
                                    <CardContent>
                                    <Typography gutterBottom variant="h5" component="div">
                                        {device.name } 
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
        
      );
}
