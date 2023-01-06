
import { useEffect, useState } from 'react'
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Box, Button, CardActionArea, Grid, TextField } from '@mui/material'; 
import axios from 'axios';
import AuthService from '../authorization/AuthService';
import { House } from '../interfaces';
import { houseUser_url, house_url } from '../urls';
import { Link } from 'react-router-dom';

export default function ManageHouses() {

    const [houses, setHouses] = useState<Array<House>>([]);
    const [homeName, setTextValue] = useState<string>("");
    const onTextChange = (e: any) => setTextValue(e.target.value);
    
    useEffect(() => {
        
        axios.get(houseUser_url+'/userId='+AuthService.getLoggedUser().id.toString() , {})
        .then((response) => response.data)
        .then((data) => {setHouses(data)})

    }, []);

    const createHouse=(name:string)=>{  
        let msg = {
            'ownerId':AuthService.getLoggedUser().id.toString(),
            'name': name,
            }
            JSON.stringify(msg)
            return axios.post(house_url ,msg);   
    }
    const handleCreateNewHome=()=>{
        createHouse(homeName);
    }

    return (
        <Grid container spacing={3}>

            <Grid justifyContent="center" container item >
                <Typography  variant="h6">Your current houses: </Typography>
                <Grid container justifyContent="center" spacing={3}>
                {houses?.map((house)=>
                    <Grid item>
                        <Link to={"/home/"+house.id}> 
                            <Card>
                                <CardActionArea>
                                    <CardMedia
                                    component="img"
                                    height="300"
                                    width ="300"
                                    image="https://www.houseiq.pl/userdata/public/gfx/14909.jpg"
                                    />
                                    <CardContent>
                                    <Typography gutterBottom variant="h5" component="div">
                                        {house.name}
                                    </Typography>
                                    </CardContent>
                                </CardActionArea>
                            </Card>
                        </Link>
                    </Grid>
                )}
                </Grid>
            </Grid>

            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Add new home</Typography>
            </Grid>
            <Grid justifyContent="center" container item xs={12}>

                <TextField  onChange={onTextChange} value={homeName} label={"home name"}/>
                <Button variant="contained" onClick={handleCreateNewHome}>Add new House</Button>           
            </Grid>

        </Grid>
        
      );
}
