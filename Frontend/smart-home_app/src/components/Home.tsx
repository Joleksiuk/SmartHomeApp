
import { useEffect, useState } from 'react'
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Alert, Box, Button, CardActionArea, Grid, LinearProgress, Stack, TextField } from '@mui/material'; 
import axios from 'axios';
import { Component, Device, DeviceDto, House, HouseDto, Scene } from '../interfaces';
import { component_url, device_url, house_url, scene_url } from '../urls';
import { Link, useNavigate, useParams } from 'react-router-dom';
import AuthService from '../authorization/AuthService';
import AddNewDevice from './AddNewDevice';
import HomeService from '../Services/HomeService';
import CurrentScenes from './CurrentScenes';
import CurrentDevices from './CurrentDevices';


export default function Home() {

    const { id } = useParams();

    const [data, setData]=useState<HouseDto>();
    const [dataFetched, setDataFetched]=useState<boolean>(false);

    const [devices, setDevices]=useState<DeviceDto[]>([])
    const [components, setComponents]=useState<Component[]>([])
    const [home, setHome]=useState<House>();
    const [scenes, setScenes] =  useState<Scene[]>();
    const [sceneName, setSceneName] = useState<string>();
    const [loading, setLoading]=useState<boolean>(true);
    const [deviceAdded, setAddNewDevice]=useState<boolean>(false);

    useEffect(() => {
        setHouseData()
    }, []);

    const setHouseData=async()=>{
        if(id!=undefined){
            let response:HouseDto = await HomeService.getHomeData(id);
            setData(response);
            setDataFetched(true);
        }
    }

    useEffect(() => {    
        if(data!=undefined && data.components!=undefined && data.devices!=undefined){
            setHome(data.house);
            setComponents(data.components);
            setDevices(data.devices);
            setScenes(data.scenes);    
            setLoading(false);      
        }     
    }, [dataFetched]);
    
    const onTextChange = (e: any) => setSceneName(e.target.value);

    const handleCreateNewScene=()=>{
        let msg = {
            'houseId': home?.id,
            'ownerId':AuthService.getLoggedUser().id.toString(),        
            'name': sceneName
            }
        JSON.stringify(msg)
        axios.post(scene_url ,msg);   
    }

    return (
        <Grid container spacing={3}>
        {!loading ?
            <Grid container spacing={3}>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h4" >Welcome to {home?.name}</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>
               <CurrentDevices devices={devices}/>
            </Grid> 
            <Grid justifyContent="center" container item xs={12}>
                <CurrentScenes scenes={scenes}/>
            </Grid>
           
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Add new scene</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>
                <TextField  onChange={onTextChange} value={sceneName} label={"home name"}/>
                <Button variant="contained" onClick={()=>handleCreateNewScene()}>Add new Scene</Button>           
            </Grid>

            <Grid justifyContent="center" container item xs={12}>
                <Link to={'/house/users/'+ id}>
                    <Button variant="contained">Manage Roles</Button>     
                </Link>           
            </Grid>

            <Grid justifyContent="center" container item xs={12}>
                <AddNewDevice home ={home} deviceAdded={deviceAdded} components={components}/>
            </Grid>
        </Grid>        
      :
      <Box sx={{ width: '100%' }}>
      <LinearProgress />
      </Box>
      
    }
</Grid>
);
}
