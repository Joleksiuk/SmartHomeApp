import { Button, Card, CardActionArea, CardContent, CardMedia, Grid, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { DeviceDto, Scene } from "../../interfaces";
import DeleteIcon from '@mui/icons-material/Delete';

export interface CurrentDevicesProps{
    devices?:DeviceDto[];
}

export default function CurrentDevices(props?:CurrentDevicesProps) {

    const [devices, setDevices] =  useState<DeviceDto[]>();

    useEffect(() => {
        if(props!=undefined && props?.devices!=undefined)
            setDevices(props?.devices)
    }, []);
    
    const navigate = useNavigate();
    
    const handleDeviceClicked=(device:DeviceDto)=>{
        navigate('/'+device.componentName.replace(/\s/g,'')+'/'+device.id)
    }

    return (
        <Grid justifyContent="center" container item xs={12}>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Your current devices</Typography>
            </Grid>
            <br></br>
            <br></br>
            <br></br>
            <Grid sx={{ flexGrow: 1 }} container spacing={2}>
                <Grid item xs={12}>
                    <Grid container justifyContent="center" spacing={2}>
                    {devices?.map((device)=>
                        <Button onClick={()=>handleDeviceClicked(device)}>
                            <Grid item>
                                <Card>
                                    <CardActionArea>
                                        <CardMedia component="img" src = {device.imagePath} height="150"/>
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
            </Grid> 
        </Grid> 
      );
}
