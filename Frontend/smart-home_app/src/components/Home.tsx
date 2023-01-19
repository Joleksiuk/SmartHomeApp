
import { useEffect, useState } from 'react'
import Typography from '@mui/material/Typography';
import { Box, Button, Grid, LinearProgress, TextField } from '@mui/material'; 
import axios from 'axios';
import { Component,  DeviceDto, House, HouseDto, Scene } from '../interfaces';
import { scene_url } from '../urls';
import { Link, useParams } from 'react-router-dom';
import AuthService from '../authorization/AuthService';
import AddNewDevice from './AddNewDevice';
import HomeService from '../Services/HomeService';
import CurrentScenes from './current/CurrentScenes';
import CurrentDevices from './current/CurrentDevices';
import DeleteHouse from './delete/DeleteHouse';


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
    const [deviceAdded, setAddNewDevice]=useState<boolean>(true);

    const [role, setRole]=useState<string>("Guest");

    useEffect(() => {
        setHouseData();
        getRole();
    }, []);

    const setHouseData=async()=>{
        if(id!=undefined){
            let response= await HomeService.getHomeData(id);
            setData(response);
            setDataFetched(true);
        }
    }

    const getRole=async()=>{
        if(id!=undefined){
            let response= await HomeService.getHomeUserRole(id);
            setRole(response);
            console.log(response)
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
            {(role=='Resident' || role=='Admin') &&
            <Grid justifyContent="center" container item xs={12}>
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
            </Grid>
            }
            {role=='Admin' &&
             <Grid justifyContent="center" container item xs={12}>
                <Grid justifyContent="center" container item xs={12}>
                    <Link to={'/house/users/'+ id}>
                        <Button variant="contained">Manage Roles</Button>     
                    </Link>           
                </Grid>

                <Grid justifyContent="center" container item xs={12}>
                    <AddNewDevice home ={home} deviceAdded={deviceAdded} components={components}/>
                    <br></br>
                    <br></br>
                    <DeleteHouse houseDto={data}/>
                </Grid>
            </Grid>
            }
        </Grid>        
      :
      <Box sx={{ width: '100%' }}>
      <LinearProgress />
      </Box>
      
    }
</Grid>
);
}
